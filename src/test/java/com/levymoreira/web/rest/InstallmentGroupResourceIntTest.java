package com.levymoreira.web.rest;

import com.levymoreira.DlFinancialControlApp;

import com.levymoreira.domain.InstallmentGroup;
import com.levymoreira.repository.InstallmentGroupRepository;
import com.levymoreira.service.InstallmentGroupService;
import com.levymoreira.service.dto.InstallmentGroupDTO;
import com.levymoreira.service.mapper.InstallmentGroupMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static com.levymoreira.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InstallmentGroupResource REST controller.
 *
 * @see InstallmentGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DlFinancialControlApp.class)
public class InstallmentGroupResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_INSTALLMENTS = 1;
    private static final Integer UPDATED_INSTALLMENTS = 2;

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    @Autowired
    private InstallmentGroupRepository installmentGroupRepository;

    @Autowired
    private InstallmentGroupMapper installmentGroupMapper;

    @Autowired
    private InstallmentGroupService installmentGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInstallmentGroupMockMvc;

    private InstallmentGroup installmentGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InstallmentGroupResource installmentGroupResource = new InstallmentGroupResource(installmentGroupService);
        this.restInstallmentGroupMockMvc = MockMvcBuilders.standaloneSetup(installmentGroupResource)
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
    public static InstallmentGroup createEntity(EntityManager em) {
        InstallmentGroup installmentGroup = new InstallmentGroup()
            .date(DEFAULT_DATE)
            .description(DEFAULT_DESCRIPTION)
            .installments(DEFAULT_INSTALLMENTS)
            .amount(DEFAULT_AMOUNT);
        return installmentGroup;
    }

    @Before
    public void initTest() {
        installmentGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstallmentGroup() throws Exception {
        int databaseSizeBeforeCreate = installmentGroupRepository.findAll().size();

        // Create the InstallmentGroup
        InstallmentGroupDTO installmentGroupDTO = installmentGroupMapper.installmentGroupToInstallmentGroupDTO(installmentGroup);
        restInstallmentGroupMockMvc.perform(post("/api/installment-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(installmentGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the InstallmentGroup in the database
        List<InstallmentGroup> installmentGroupList = installmentGroupRepository.findAll();
        assertThat(installmentGroupList).hasSize(databaseSizeBeforeCreate + 1);
        InstallmentGroup testInstallmentGroup = installmentGroupList.get(installmentGroupList.size() - 1);
        assertThat(testInstallmentGroup.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testInstallmentGroup.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInstallmentGroup.getInstallments()).isEqualTo(DEFAULT_INSTALLMENTS);
        assertThat(testInstallmentGroup.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createInstallmentGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = installmentGroupRepository.findAll().size();

        // Create the InstallmentGroup with an existing ID
        installmentGroup.setId(1L);
        InstallmentGroupDTO installmentGroupDTO = installmentGroupMapper.installmentGroupToInstallmentGroupDTO(installmentGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstallmentGroupMockMvc.perform(post("/api/installment-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(installmentGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<InstallmentGroup> installmentGroupList = installmentGroupRepository.findAll();
        assertThat(installmentGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInstallmentGroups() throws Exception {
        // Initialize the database
        installmentGroupRepository.saveAndFlush(installmentGroup);

        // Get all the installmentGroupList
        restInstallmentGroupMockMvc.perform(get("/api/installment-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(installmentGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].installments").value(hasItem(DEFAULT_INSTALLMENTS)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void getInstallmentGroup() throws Exception {
        // Initialize the database
        installmentGroupRepository.saveAndFlush(installmentGroup);

        // Get the installmentGroup
        restInstallmentGroupMockMvc.perform(get("/api/installment-groups/{id}", installmentGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(installmentGroup.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.installments").value(DEFAULT_INSTALLMENTS))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInstallmentGroup() throws Exception {
        // Get the installmentGroup
        restInstallmentGroupMockMvc.perform(get("/api/installment-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstallmentGroup() throws Exception {
        // Initialize the database
        installmentGroupRepository.saveAndFlush(installmentGroup);
        int databaseSizeBeforeUpdate = installmentGroupRepository.findAll().size();

        // Update the installmentGroup
        InstallmentGroup updatedInstallmentGroup = installmentGroupRepository.findOne(installmentGroup.getId());
        updatedInstallmentGroup
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .installments(UPDATED_INSTALLMENTS)
            .amount(UPDATED_AMOUNT);
        InstallmentGroupDTO installmentGroupDTO = installmentGroupMapper.installmentGroupToInstallmentGroupDTO(updatedInstallmentGroup);

        restInstallmentGroupMockMvc.perform(put("/api/installment-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(installmentGroupDTO)))
            .andExpect(status().isOk());

        // Validate the InstallmentGroup in the database
        List<InstallmentGroup> installmentGroupList = installmentGroupRepository.findAll();
        assertThat(installmentGroupList).hasSize(databaseSizeBeforeUpdate);
        InstallmentGroup testInstallmentGroup = installmentGroupList.get(installmentGroupList.size() - 1);
        assertThat(testInstallmentGroup.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testInstallmentGroup.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInstallmentGroup.getInstallments()).isEqualTo(UPDATED_INSTALLMENTS);
        assertThat(testInstallmentGroup.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingInstallmentGroup() throws Exception {
        int databaseSizeBeforeUpdate = installmentGroupRepository.findAll().size();

        // Create the InstallmentGroup
        InstallmentGroupDTO installmentGroupDTO = installmentGroupMapper.installmentGroupToInstallmentGroupDTO(installmentGroup);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInstallmentGroupMockMvc.perform(put("/api/installment-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(installmentGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the InstallmentGroup in the database
        List<InstallmentGroup> installmentGroupList = installmentGroupRepository.findAll();
        assertThat(installmentGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInstallmentGroup() throws Exception {
        // Initialize the database
        installmentGroupRepository.saveAndFlush(installmentGroup);
        int databaseSizeBeforeDelete = installmentGroupRepository.findAll().size();

        // Get the installmentGroup
        restInstallmentGroupMockMvc.perform(delete("/api/installment-groups/{id}", installmentGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InstallmentGroup> installmentGroupList = installmentGroupRepository.findAll();
        assertThat(installmentGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstallmentGroup.class);
    }
}
