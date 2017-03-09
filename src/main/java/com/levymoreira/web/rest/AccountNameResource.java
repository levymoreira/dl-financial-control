package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.domain.AccountName;

import com.levymoreira.repository.AccountNameRepository;
import com.levymoreira.repository.search.AccountNameSearchRepository;
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
 * REST controller for managing AccountName.
 */
@RestController
@RequestMapping("/api")
public class AccountNameResource {

    private final Logger log = LoggerFactory.getLogger(AccountNameResource.class);

    private static final String ENTITY_NAME = "accountName";
        
    private final AccountNameRepository accountNameRepository;

    private final AccountNameSearchRepository accountNameSearchRepository;

    public AccountNameResource(AccountNameRepository accountNameRepository, AccountNameSearchRepository accountNameSearchRepository) {
        this.accountNameRepository = accountNameRepository;
        this.accountNameSearchRepository = accountNameSearchRepository;
    }

    /**
     * POST  /account-names : Create a new accountName.
     *
     * @param accountName the accountName to create
     * @return the ResponseEntity with status 201 (Created) and with body the new accountName, or with status 400 (Bad Request) if the accountName has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/account-names")
    @Timed
    public ResponseEntity<AccountName> createAccountName(@RequestBody AccountName accountName) throws URISyntaxException {
        log.debug("REST request to save AccountName : {}", accountName);
        if (accountName.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new accountName cannot already have an ID")).body(null);
        }
        AccountName result = accountNameRepository.save(accountName);
        accountNameSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/account-names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /account-names : Updates an existing accountName.
     *
     * @param accountName the accountName to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated accountName,
     * or with status 400 (Bad Request) if the accountName is not valid,
     * or with status 500 (Internal Server Error) if the accountName couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/account-names")
    @Timed
    public ResponseEntity<AccountName> updateAccountName(@RequestBody AccountName accountName) throws URISyntaxException {
        log.debug("REST request to update AccountName : {}", accountName);
        if (accountName.getId() == null) {
            return createAccountName(accountName);
        }
        AccountName result = accountNameRepository.save(accountName);
        accountNameSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, accountName.getId().toString()))
            .body(result);
    }

    /**
     * GET  /account-names : get all the accountNames.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of accountNames in body
     */
    @GetMapping("/account-names")
    @Timed
    public List<AccountName> getAllAccountNames() {
        log.debug("REST request to get all AccountNames");
        List<AccountName> accountNames = accountNameRepository.findAll();
        return accountNames;
    }

    /**
     * GET  /account-names/:id : get the "id" accountName.
     *
     * @param id the id of the accountName to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the accountName, or with status 404 (Not Found)
     */
    @GetMapping("/account-names/{id}")
    @Timed
    public ResponseEntity<AccountName> getAccountName(@PathVariable Long id) {
        log.debug("REST request to get AccountName : {}", id);
        AccountName accountName = accountNameRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(accountName));
    }

    /**
     * DELETE  /account-names/:id : delete the "id" accountName.
     *
     * @param id the id of the accountName to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/account-names/{id}")
    @Timed
    public ResponseEntity<Void> deleteAccountName(@PathVariable Long id) {
        log.debug("REST request to delete AccountName : {}", id);
        accountNameRepository.delete(id);
        accountNameSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/account-names?query=:query : search for the accountName corresponding
     * to the query.
     *
     * @param query the query of the accountName search 
     * @return the result of the search
     */
    @GetMapping("/_search/account-names")
    @Timed
    public List<AccountName> searchAccountNames(@RequestParam String query) {
        log.debug("REST request to search AccountNames for query {}", query);
        return StreamSupport
            .stream(accountNameSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
