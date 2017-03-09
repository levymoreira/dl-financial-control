package com.levymoreira.web.rest;

import com.levymoreira.DlFinancialControlApp;

import com.levymoreira.domain.AccountName;
import com.levymoreira.repository.AccountNameRepository;
import com.levymoreira.repository.search.AccountNameSearchRepository;
import com.levymoreira.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AccountNameResource REST controller.
 *
 * @see AccountNameResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DlFinancialControlApp.class)
public class AccountNameResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AccountNameRepository accountNameRepository;

    @Autowired
    private AccountNameSearchRepository accountNameSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAccountNameMockMvc;

    private AccountName accountName;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            AccountNameResource accountNameResource = new AccountNameResource(accountNameRepository, accountNameSearchRepository);
        this.restAccountNameMockMvc = MockMvcBuilders.standaloneSetup(accountNameResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountName createEntity(EntityManager em) {
        AccountName accountName = new AccountName()
                .description(DEFAULT_DESCRIPTION);
        return accountName;
    }

    @Before
    public void initTest() {
        accountNameSearchRepository.deleteAll();
        accountName = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountName() throws Exception {
        int databaseSizeBeforeCreate = accountNameRepository.findAll().size();

        // Create the AccountName

        restAccountNameMockMvc.perform(post("/api/account-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountName)))
            .andExpect(status().isCreated());

        // Validate the AccountName in the database
        List<AccountName> accountNameList = accountNameRepository.findAll();
        assertThat(accountNameList).hasSize(databaseSizeBeforeCreate + 1);
        AccountName testAccountName = accountNameList.get(accountNameList.size() - 1);
        assertThat(testAccountName.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);

        // Validate the AccountName in Elasticsearch
        AccountName accountNameEs = accountNameSearchRepository.findOne(testAccountName.getId());
        assertThat(accountNameEs).isEqualToComparingFieldByField(testAccountName);
    }

    @Test
    @Transactional
    public void createAccountNameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountNameRepository.findAll().size();

        // Create the AccountName with an existing ID
        AccountName existingAccountName = new AccountName();
        existingAccountName.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountNameMockMvc.perform(post("/api/account-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAccountName)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AccountName> accountNameList = accountNameRepository.findAll();
        assertThat(accountNameList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAccountNames() throws Exception {
        // Initialize the database
        accountNameRepository.saveAndFlush(accountName);

        // Get all the accountNameList
        restAccountNameMockMvc.perform(get("/api/account-names?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountName.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getAccountName() throws Exception {
        // Initialize the database
        accountNameRepository.saveAndFlush(accountName);

        // Get the accountName
        restAccountNameMockMvc.perform(get("/api/account-names/{id}", accountName.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountName.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountName() throws Exception {
        // Get the accountName
        restAccountNameMockMvc.perform(get("/api/account-names/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountName() throws Exception {
        // Initialize the database
        accountNameRepository.saveAndFlush(accountName);
        accountNameSearchRepository.save(accountName);
        int databaseSizeBeforeUpdate = accountNameRepository.findAll().size();

        // Update the accountName
        AccountName updatedAccountName = accountNameRepository.findOne(accountName.getId());
        updatedAccountName
                .description(UPDATED_DESCRIPTION);

        restAccountNameMockMvc.perform(put("/api/account-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAccountName)))
            .andExpect(status().isOk());

        // Validate the AccountName in the database
        List<AccountName> accountNameList = accountNameRepository.findAll();
        assertThat(accountNameList).hasSize(databaseSizeBeforeUpdate);
        AccountName testAccountName = accountNameList.get(accountNameList.size() - 1);
        assertThat(testAccountName.getDescription()).isEqualTo(UPDATED_DESCRIPTION);

        // Validate the AccountName in Elasticsearch
        AccountName accountNameEs = accountNameSearchRepository.findOne(testAccountName.getId());
        assertThat(accountNameEs).isEqualToComparingFieldByField(testAccountName);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountName() throws Exception {
        int databaseSizeBeforeUpdate = accountNameRepository.findAll().size();

        // Create the AccountName

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAccountNameMockMvc.perform(put("/api/account-names")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountName)))
            .andExpect(status().isCreated());

        // Validate the AccountName in the database
        List<AccountName> accountNameList = accountNameRepository.findAll();
        assertThat(accountNameList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAccountName() throws Exception {
        // Initialize the database
        accountNameRepository.saveAndFlush(accountName);
        accountNameSearchRepository.save(accountName);
        int databaseSizeBeforeDelete = accountNameRepository.findAll().size();

        // Get the accountName
        restAccountNameMockMvc.perform(delete("/api/account-names/{id}", accountName.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean accountNameExistsInEs = accountNameSearchRepository.exists(accountName.getId());
        assertThat(accountNameExistsInEs).isFalse();

        // Validate the database is empty
        List<AccountName> accountNameList = accountNameRepository.findAll();
        assertThat(accountNameList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAccountName() throws Exception {
        // Initialize the database
        accountNameRepository.saveAndFlush(accountName);
        accountNameSearchRepository.save(accountName);

        // Search the accountName
        restAccountNameMockMvc.perform(get("/api/_search/account-names?query=id:" + accountName.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountName.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountName.class);
    }
}
