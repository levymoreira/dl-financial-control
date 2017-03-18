package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.service.AccountNameService;
import com.levymoreira.web.rest.util.HeaderUtil;
import com.levymoreira.web.rest.util.PaginationUtil;
import com.levymoreira.service.dto.AccountNameDTO;
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
 * REST controller for managing AccountName.
 */
@RestController
@RequestMapping("/api")
public class AccountNameResource {

    private final Logger log = LoggerFactory.getLogger(AccountNameResource.class);

    private static final String ENTITY_NAME = "accountName";
        
    private final AccountNameService accountNameService;

    public AccountNameResource(AccountNameService accountNameService) {
        this.accountNameService = accountNameService;
    }

    /**
     * POST  /account-names : Create a new accountName.
     *
     * @param accountNameDTO the accountNameDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accountNameDTO, or with status 400 (Bad Request) if the accountName has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/account-names")
    @Timed
    public ResponseEntity<AccountNameDTO> createAccountName(@RequestBody AccountNameDTO accountNameDTO) throws URISyntaxException {
        log.debug("REST request to save AccountName : {}", accountNameDTO);
        if (accountNameDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new accountName cannot already have an ID")).body(null);
        }
        AccountNameDTO result = accountNameService.save(accountNameDTO);
        return ResponseEntity.created(new URI("/api/account-names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /account-names : Updates an existing accountName.
     *
     * @param accountNameDTO the accountNameDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accountNameDTO,
     * or with status 400 (Bad Request) if the accountNameDTO is not valid,
     * or with status 500 (Internal Server Error) if the accountNameDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/account-names")
    @Timed
    public ResponseEntity<AccountNameDTO> updateAccountName(@RequestBody AccountNameDTO accountNameDTO) throws URISyntaxException {
        log.debug("REST request to update AccountName : {}", accountNameDTO);
        if (accountNameDTO.getId() == null) {
            return createAccountName(accountNameDTO);
        }
        AccountNameDTO result = accountNameService.save(accountNameDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accountNameDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /account-names : get all the accountNames.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accountNames in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/account-names")
    @Timed
    public ResponseEntity<List<AccountNameDTO>> getAllAccountNames(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AccountNames");
        Page<AccountNameDTO> page = accountNameService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/account-names");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /account-names/:id : get the "id" accountName.
     *
     * @param id the id of the accountNameDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accountNameDTO, or with status 404 (Not Found)
     */
    @GetMapping("/account-names/{id}")
    @Timed
    public ResponseEntity<AccountNameDTO> getAccountName(@PathVariable Long id) {
        log.debug("REST request to get AccountName : {}", id);
        AccountNameDTO accountNameDTO = accountNameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(accountNameDTO));
    }

    /**
     * DELETE  /account-names/:id : delete the "id" accountName.
     *
     * @param id the id of the accountNameDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/account-names/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccountName(@PathVariable Long id) {
        log.debug("REST request to delete AccountName : {}", id);
        accountNameService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
