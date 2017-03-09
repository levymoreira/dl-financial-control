package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.domain.CreditCardInvoice;

import com.levymoreira.repository.CreditCardInvoiceRepository;
import com.levymoreira.repository.search.CreditCardInvoiceSearchRepository;
import com.levymoreira.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CreditCardInvoice.
 */
@RestController
@RequestMapping("/api")
public class CreditCardInvoiceResource {

    private final Logger log = LoggerFactory.getLogger(CreditCardInvoiceResource.class);

    private static final String ENTITY_NAME = "creditCardInvoice";
        
    private final CreditCardInvoiceRepository creditCardInvoiceRepository;

    private final CreditCardInvoiceSearchRepository creditCardInvoiceSearchRepository;

    public CreditCardInvoiceResource(CreditCardInvoiceRepository creditCardInvoiceRepository, CreditCardInvoiceSearchRepository creditCardInvoiceSearchRepository) {
        this.creditCardInvoiceRepository = creditCardInvoiceRepository;
        this.creditCardInvoiceSearchRepository = creditCardInvoiceSearchRepository;
    }

    /**
     * POST  /credit-card-invoices : Create a new creditCardInvoice.
     *
     * @param creditCardInvoice the creditCardInvoice to create
     * @return the ResponseEntity with status 201 (Created) and with body the new creditCardInvoice, or with status 400 (Bad Request) if the creditCardInvoice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/credit-card-invoices")
    @Timed
    public ResponseEntity<CreditCardInvoice> createCreditCardInvoice(@RequestBody CreditCardInvoice creditCardInvoice) throws URISyntaxException {
        log.debug("REST request to save CreditCardInvoice : {}", creditCardInvoice);
        if (creditCardInvoice.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new creditCardInvoice cannot already have an ID")).body(null);
        }
        CreditCardInvoice result = creditCardInvoiceRepository.save(creditCardInvoice);
        creditCardInvoiceSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/credit-card-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /credit-card-invoices : Updates an existing creditCardInvoice.
     *
     * @param creditCardInvoice the creditCardInvoice to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated creditCardInvoice,
     * or with status 400 (Bad Request) if the creditCardInvoice is not valid,
     * or with status 500 (Internal Server Error) if the creditCardInvoice couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/credit-card-invoices")
    @Timed
    public ResponseEntity<CreditCardInvoice> updateCreditCardInvoice(@RequestBody CreditCardInvoice creditCardInvoice) throws URISyntaxException {
        log.debug("REST request to update CreditCardInvoice : {}", creditCardInvoice);
        if (creditCardInvoice.getId() == null) {
            return createCreditCardInvoice(creditCardInvoice);
        }
        CreditCardInvoice result = creditCardInvoiceRepository.save(creditCardInvoice);
        creditCardInvoiceSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, creditCardInvoice.getId().toString()))
            .body(result);
    }

    /**
     * GET  /credit-card-invoices : get all the creditCardInvoices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of creditCardInvoices in body
     */
    @GetMapping("/credit-card-invoices")
    @Timed
    public List<CreditCardInvoice> getAllCreditCardInvoices() {
        log.debug("REST request to get all CreditCardInvoices");
        List<CreditCardInvoice> creditCardInvoices = creditCardInvoiceRepository.findAll();
        return creditCardInvoices;
    }

    /**
     * GET  /credit-card-invoices/:id : get the "id" creditCardInvoice.
     *
     * @param id the id of the creditCardInvoice to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the creditCardInvoice, or with status 404 (Not Found)
     */
    @GetMapping("/credit-card-invoices/{id}")
    @Timed
    public ResponseEntity<CreditCardInvoice> getCreditCardInvoice(@PathVariable Long id) {
        log.debug("REST request to get CreditCardInvoice : {}", id);
        CreditCardInvoice creditCardInvoice = creditCardInvoiceRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(creditCardInvoice));
    }

    /**
     * DELETE  /credit-card-invoices/:id : delete the "id" creditCardInvoice.
     *
     * @param id the id of the creditCardInvoice to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/credit-card-invoices/{id}")
    @Timed
    public ResponseEntity<Void> deleteCreditCardInvoice(@PathVariable Long id) {
        log.debug("REST request to delete CreditCardInvoice : {}", id);
        creditCardInvoiceRepository.delete(id);
        creditCardInvoiceSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/credit-card-invoices?query=:query : search for the creditCardInvoice corresponding
     * to the query.
     *
     * @param query the query of the creditCardInvoice search 
     * @return the result of the search
     */
    @GetMapping("/_search/credit-card-invoices")
    @Timed
    public List<CreditCardInvoice> searchCreditCardInvoices(@RequestParam String query) {
        log.debug("REST request to search CreditCardInvoices for query {}", query);
        return StreamSupport
            .stream(creditCardInvoiceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
