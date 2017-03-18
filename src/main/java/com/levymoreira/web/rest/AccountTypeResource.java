package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.domain.AccountType;

import com.levymoreira.repository.AccountTypeRepository;
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

/**
 * REST controller for managing AccountType.
 */
@RestController
@RequestMapping("/api")
public class AccountTypeResource {

    private final Logger log = LoggerFactory.getLogger(AccountTypeResource.class);

    private static final String ENTITY_NAME = "accountType";
        
    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeResource(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    /**
     * POST  /account-types : Create a new accountType.
     *
     * @param accountType the accountType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accountType, or with status 400 (Bad Request) if the accountType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/account-types")
    @Timed
    public ResponseEntity<AccountType> createAccountType(@RequestBody AccountType accountType) throws URISyntaxException {
        log.debug("REST request to save AccountType : {}", accountType);
        if (accountType.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new accountType cannot already have an ID")).body(null);
        }
        AccountType result = accountTypeRepository.save(accountType);
        return ResponseEntity.created(new URI("/api/account-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /account-types : Updates an existing accountType.
     *
     * @param accountType the accountType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accountType,
     * or with status 400 (Bad Request) if the accountType is not valid,
     * or with status 500 (Internal Server Error) if the accountType couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/account-types")
    @Timed
    public ResponseEntity<AccountType> updateAccountType(@RequestBody AccountType accountType) throws URISyntaxException {
        log.debug("REST request to update AccountType : {}", accountType);
        if (accountType.getId() == null) {
            return createAccountType(accountType);
        }
        AccountType result = accountTypeRepository.save(accountType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accountType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /account-types : get all the accountTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of accountTypes in body
     */
    @GetMapping("/account-types")
    @Timed
    public List<AccountType> getAllAccountTypes() {
        log.debug("REST request to get all AccountTypes");
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypes;
    }

    /**
     * GET  /account-types/:id : get the "id" accountType.
     *
     * @param id the id of the accountType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accountType, or with status 404 (Not Found)
     */
    @GetMapping("/account-types/{id}")
    @Timed
    public ResponseEntity<AccountType> getAccountType(@PathVariable Long id) {
        log.debug("REST request to get AccountType : {}", id);
        AccountType accountType = accountTypeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(accountType));
    }

    /**
     * DELETE  /account-types/:id : delete the "id" accountType.
     *
     * @param id the id of the accountType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/account-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccountType(@PathVariable Long id) {
        log.debug("REST request to delete AccountType : {}", id);
        accountTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
