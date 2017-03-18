package com.levymoreira.web.rest;

import com.levymoreira.DlFinancialControlApp;

import com.levymoreira.domain.CreditCardInvoice;
import com.levymoreira.repository.CreditCardInvoiceRepository;
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
 * Test class for the CreditCardInvoiceResource REST controller.
 *
 * @see CreditCardInvoiceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DlFinancialControlApp.class)
public class CreditCardInvoiceResourceIntTest {

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final Integer DEFAULT_MONTH = 1;
    private static final Integer UPDATED_MONTH = 2;

    @Autowired
    private CreditCardInvoiceRepository creditCardInvoiceRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCreditCardInvoiceMockMvc;

    private CreditCardInvoice creditCardInvoice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CreditCardInvoiceResource creditCardInvoiceResource = new CreditCardInvoiceResource(creditCardInvoiceRepository);
        this.restCreditCardInvoiceMockMvc = MockMvcBuilders.standaloneSetup(creditCardInvoiceResource)
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
    public static CreditCardInvoice createEntity(EntityManager em) {
        CreditCardInvoice creditCardInvoice = new CreditCardInvoice()
            .year(DEFAULT_YEAR)
            .month(DEFAULT_MONTH);
        return creditCardInvoice;
    }

    @Before
    public void initTest() {
        creditCardInvoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreditCardInvoice() throws Exception {
        int databaseSizeBeforeCreate = creditCardInvoiceRepository.findAll().size();

        // Create the CreditCardInvoice
        restCreditCardInvoiceMockMvc.perform(post("/api/credit-card-invoices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditCardInvoice)))
            .andExpect(status().isCreated());

        // Validate the CreditCardInvoice in the database
        List<CreditCardInvoice> creditCardInvoiceList = creditCardInvoiceRepository.findAll();
        assertThat(creditCardInvoiceList).hasSize(databaseSizeBeforeCreate + 1);
        CreditCardInvoice testCreditCardInvoice = creditCardInvoiceList.get(creditCardInvoiceList.size() - 1);
        assertThat(testCreditCardInvoice.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testCreditCardInvoice.getMonth()).isEqualTo(DEFAULT_MONTH);
    }

    @Test
    @Transactional
    public void createCreditCardInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creditCardInvoiceRepository.findAll().size();

        // Create the CreditCardInvoice with an existing ID
        creditCardInvoice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditCardInvoiceMockMvc.perform(post("/api/credit-card-invoices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditCardInvoice)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CreditCardInvoice> creditCardInvoiceList = creditCardInvoiceRepository.findAll();
        assertThat(creditCardInvoiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCreditCardInvoices() throws Exception {
        // Initialize the database
        creditCardInvoiceRepository.saveAndFlush(creditCardInvoice);

        // Get all the creditCardInvoiceList
        restCreditCardInvoiceMockMvc.perform(get("/api/credit-card-invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditCardInvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)));
    }

    @Test
    @Transactional
    public void getCreditCardInvoice() throws Exception {
        // Initialize the database
        creditCardInvoiceRepository.saveAndFlush(creditCardInvoice);

        // Get the creditCardInvoice
        restCreditCardInvoiceMockMvc.perform(get("/api/credit-card-invoices/{id}", creditCardInvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(creditCardInvoice.getId().intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH));
    }

    @Test
    @Transactional
    public void getNonExistingCreditCardInvoice() throws Exception {
        // Get the creditCardInvoice
        restCreditCardInvoiceMockMvc.perform(get("/api/credit-card-invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreditCardInvoice() throws Exception {
        // Initialize the database
        creditCardInvoiceRepository.saveAndFlush(creditCardInvoice);
        int databaseSizeBeforeUpdate = creditCardInvoiceRepository.findAll().size();

        // Update the creditCardInvoice
        CreditCardInvoice updatedCreditCardInvoice = creditCardInvoiceRepository.findOne(creditCardInvoice.getId());
        updatedCreditCardInvoice
            .year(UPDATED_YEAR)
            .month(UPDATED_MONTH);

        restCreditCardInvoiceMockMvc.perform(put("/api/credit-card-invoices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCreditCardInvoice)))
            .andExpect(status().isOk());

        // Validate the CreditCardInvoice in the database
        List<CreditCardInvoice> creditCardInvoiceList = creditCardInvoiceRepository.findAll();
        assertThat(creditCardInvoiceList).hasSize(databaseSizeBeforeUpdate);
        CreditCardInvoice testCreditCardInvoice = creditCardInvoiceList.get(creditCardInvoiceList.size() - 1);
        assertThat(testCreditCardInvoice.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testCreditCardInvoice.getMonth()).isEqualTo(UPDATED_MONTH);
    }

    @Test
    @Transactional
    public void updateNonExistingCreditCardInvoice() throws Exception {
        int databaseSizeBeforeUpdate = creditCardInvoiceRepository.findAll().size();

        // Create the CreditCardInvoice

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCreditCardInvoiceMockMvc.perform(put("/api/credit-card-invoices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(creditCardInvoice)))
            .andExpect(status().isCreated());

        // Validate the CreditCardInvoice in the database
        List<CreditCardInvoice> creditCardInvoiceList = creditCardInvoiceRepository.findAll();
        assertThat(creditCardInvoiceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCreditCardInvoice() throws Exception {
        // Initialize the database
        creditCardInvoiceRepository.saveAndFlush(creditCardInvoice);
        int databaseSizeBeforeDelete = creditCardInvoiceRepository.findAll().size();

        // Get the creditCardInvoice
        restCreditCardInvoiceMockMvc.perform(delete("/api/credit-card-invoices/{id}", creditCardInvoice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CreditCardInvoice> creditCardInvoiceList = creditCardInvoiceRepository.findAll();
        assertThat(creditCardInvoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditCardInvoice.class);
    }
}
