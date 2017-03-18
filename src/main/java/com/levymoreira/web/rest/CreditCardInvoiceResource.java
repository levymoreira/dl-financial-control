package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.service.CreditCardInvoiceService;
import com.levymoreira.web.rest.util.HeaderUtil;
import com.levymoreira.web.rest.util.PaginationUtil;
import com.levymoreira.service.dto.CreditCardInvoiceDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing CreditCardInvoice.
 */
@RestController
@RequestMapping("/api")
public class CreditCardInvoiceResource {

    private final Logger log = LoggerFactory.getLogger(CreditCardInvoiceResource.class);

    private static final String ENTITY_NAME = "creditCardInvoice";
        
    private final CreditCardInvoiceService creditCardInvoiceService;

    public CreditCardInvoiceResource(CreditCardInvoiceService creditCardInvoiceService) {
        this.creditCardInvoiceService = creditCardInvoiceService;
    }

    /**
     * POST  /credit-card-invoices : Create a new creditCardInvoice.
     *
     * @param creditCardInvoiceDTO the creditCardInvoiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new creditCardInvoiceDTO, or with status 400 (Bad Request) if the creditCardInvoice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/credit-card-invoices")
    @Timed
    public ResponseEntity<CreditCardInvoiceDTO> createCreditCardInvoice(@RequestBody CreditCardInvoiceDTO creditCardInvoiceDTO) throws URISyntaxException {
        log.debug("REST request to save CreditCardInvoice : {}", creditCardInvoiceDTO);
        if (creditCardInvoiceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new creditCardInvoice cannot already have an ID")).body(null);
        }
        CreditCardInvoiceDTO result = creditCardInvoiceService.save(creditCardInvoiceDTO);
        return ResponseEntity.created(new URI("/api/credit-card-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /credit-card-invoices : Updates an existing creditCardInvoice.
     *
     * @param creditCardInvoiceDTO the creditCardInvoiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated creditCardInvoiceDTO,
     * or with status 400 (Bad Request) if the creditCardInvoiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the creditCardInvoiceDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/credit-card-invoices")
    @Timed
    public ResponseEntity<CreditCardInvoiceDTO> updateCreditCardInvoice(@RequestBody CreditCardInvoiceDTO creditCardInvoiceDTO) throws URISyntaxException {
        log.debug("REST request to update CreditCardInvoice : {}", creditCardInvoiceDTO);
        if (creditCardInvoiceDTO.getId() == null) {
            return createCreditCardInvoice(creditCardInvoiceDTO);
        }
        CreditCardInvoiceDTO result = creditCardInvoiceService.save(creditCardInvoiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, creditCardInvoiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /credit-card-invoices : get all the creditCardInvoices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of creditCardInvoices in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/credit-card-invoices")
    @Timed
    public ResponseEntity<List<CreditCardInvoiceDTO>> getAllCreditCardInvoices(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CreditCardInvoices");
        Page<CreditCardInvoiceDTO> page = creditCardInvoiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/credit-card-invoices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /credit-card-invoices/:id : get the "id" creditCardInvoice.
     *
     * @param id the id of the creditCardInvoiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the creditCardInvoiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/credit-card-invoices/{id}")
    @Timed
    public ResponseEntity<CreditCardInvoiceDTO> getCreditCardInvoice(@PathVariable Long id) {
        log.debug("REST request to get CreditCardInvoice : {}", id);
        CreditCardInvoiceDTO creditCardInvoiceDTO = creditCardInvoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(creditCardInvoiceDTO));
    }

    /**
     * DELETE  /credit-card-invoices/:id : delete the "id" creditCardInvoice.
     *
     * @param id the id of the creditCardInvoiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/credit-card-invoices/{id}")
    @Timed
    public ResponseEntity<Void> deleteCreditCardInvoice(@PathVariable Long id) {
        log.debug("REST request to delete CreditCardInvoice : {}", id);
        creditCardInvoiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
