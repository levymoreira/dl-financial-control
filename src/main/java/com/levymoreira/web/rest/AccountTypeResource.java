package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.service.AccountTypeService;
import com.levymoreira.web.rest.util.HeaderUtil;
import com.levymoreira.web.rest.util.PaginationUtil;
import com.levymoreira.service.dto.AccountTypeDTO;
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
 * REST controller for managing AccountType.
 */
@RestController
@RequestMapping("/api")
public class AccountTypeResource {

    private final Logger log = LoggerFactory.getLogger(AccountTypeResource.class);

    private static final String ENTITY_NAME = "accountType";
        
    private final AccountTypeService accountTypeService;

    public AccountTypeResource(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    /**
     * POST  /account-types : Create a new accountType.
     *
     * @param accountTypeDTO the accountTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accountTypeDTO, or with status 400 (Bad Request) if the accountType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/account-types")
    @Timed
    public ResponseEntity<AccountTypeDTO> createAccountType(@RequestBody AccountTypeDTO accountTypeDTO) throws URISyntaxException {
        log.debug("REST request to save AccountType : {}", accountTypeDTO);
        if (accountTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new accountType cannot already have an ID")).body(null);
        }
        AccountTypeDTO result = accountTypeService.save(accountTypeDTO);
        return ResponseEntity.created(new URI("/api/account-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /account-types : Updates an existing accountType.
     *
     * @param accountTypeDTO the accountTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accountTypeDTO,
     * or with status 400 (Bad Request) if the accountTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the accountTypeDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/account-types")
    @Timed
    public ResponseEntity<AccountTypeDTO> updateAccountType(@RequestBody AccountTypeDTO accountTypeDTO) throws URISyntaxException {
        log.debug("REST request to update AccountType : {}", accountTypeDTO);
        if (accountTypeDTO.getId() == null) {
            return createAccountType(accountTypeDTO);
        }
        AccountTypeDTO result = accountTypeService.save(accountTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accountTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /account-types : get all the accountTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of accountTypes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/account-types")
    @Timed
    public ResponseEntity<List<AccountTypeDTO>> getAllAccountTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AccountTypes");
        Page<AccountTypeDTO> page = accountTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/account-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /account-types/:id : get the "id" accountType.
     *
     * @param id the id of the accountTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accountTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/account-types/{id}")
    @Timed
    public ResponseEntity<AccountTypeDTO> getAccountType(@PathVariable Long id) {
        log.debug("REST request to get AccountType : {}", id);
        AccountTypeDTO accountTypeDTO = accountTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(accountTypeDTO));
    }

    /**
     * DELETE  /account-types/:id : delete the "id" accountType.
     *
     * @param id the id of the accountTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/account-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccountType(@PathVariable Long id) {
        log.debug("REST request to delete AccountType : {}", id);
        accountTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
