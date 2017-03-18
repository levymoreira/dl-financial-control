package com.levymoreira.web.rest;

import com.levymoreira.DlFinancialControlApp;

import com.levymoreira.domain.AccountType;
import com.levymoreira.repository.AccountTypeRepository;
import com.levymoreira.service.AccountTypeService;
import com.levymoreira.service.dto.AccountTypeDTO;
import com.levymoreira.service.mapper.AccountTypeMapper;
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
 * Test class for the AccountTypeResource REST controller.
 *
 * @see AccountTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DlFinancialControlApp.class)
public class AccountTypeResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountTypeMapper accountTypeMapper;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAccountTypeMockMvc;

    private AccountType accountType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AccountTypeResource accountTypeResource = new AccountTypeResource(accountTypeService);
        this.restAccountTypeMockMvc = MockMvcBuilders.standaloneSetup(accountTypeResource)
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
    public static AccountType createEntity(EntityManager em) {
        AccountType accountType = new AccountType()
            .description(DEFAULT_DESCRIPTION)
            .code(DEFAULT_CODE);
        return accountType;
    }

    @Before
    public void initTest() {
        accountType = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountType() throws Exception {
        int databaseSizeBeforeCreate = accountTypeRepository.findAll().size();

        // Create the AccountType
        AccountTypeDTO accountTypeDTO = accountTypeMapper.accountTypeToAccountTypeDTO(accountType);
        restAccountTypeMockMvc.perform(post("/api/account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AccountType testAccountType = accountTypeList.get(accountTypeList.size() - 1);
        assertThat(testAccountType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAccountType.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createAccountTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountTypeRepository.findAll().size();

        // Create the AccountType with an existing ID
        accountType.setId(1L);
        AccountTypeDTO accountTypeDTO = accountTypeMapper.accountTypeToAccountTypeDTO(accountType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountTypeMockMvc.perform(post("/api/account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAccountTypes() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);

        // Get all the accountTypeList
        restAccountTypeMockMvc.perform(get("/api/account-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountType.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())));
    }

    @Test
    @Transactional
    public void getAccountType() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);

        // Get the accountType
        restAccountTypeMockMvc.perform(get("/api/account-types/{id}", accountType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountType.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountType() throws Exception {
        // Get the accountType
        restAccountTypeMockMvc.perform(get("/api/account-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountType() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);
        int databaseSizeBeforeUpdate = accountTypeRepository.findAll().size();

        // Update the accountType
        AccountType updatedAccountType = accountTypeRepository.findOne(accountType.getId());
        updatedAccountType
            .description(UPDATED_DESCRIPTION)
            .code(UPDATED_CODE);
        AccountTypeDTO accountTypeDTO = accountTypeMapper.accountTypeToAccountTypeDTO(updatedAccountType);

        restAccountTypeMockMvc.perform(put("/api/account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isOk());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeUpdate);
        AccountType testAccountType = accountTypeList.get(accountTypeList.size() - 1);
        assertThat(testAccountType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAccountType.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountType() throws Exception {
        int databaseSizeBeforeUpdate = accountTypeRepository.findAll().size();

        // Create the AccountType
        AccountTypeDTO accountTypeDTO = accountTypeMapper.accountTypeToAccountTypeDTO(accountType);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAccountTypeMockMvc.perform(put("/api/account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountType in the database
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAccountType() throws Exception {
        // Initialize the database
        accountTypeRepository.saveAndFlush(accountType);
        int databaseSizeBeforeDelete = accountTypeRepository.findAll().size();

        // Get the accountType
        restAccountTypeMockMvc.perform(delete("/api/account-types/{id}", accountType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AccountType> accountTypeList = accountTypeRepository.findAll();
        assertThat(accountTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountType.class);
    }
}
