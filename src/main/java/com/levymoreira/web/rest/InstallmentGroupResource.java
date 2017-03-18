package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.domain.InstallmentGroup;

import com.levymoreira.repository.InstallmentGroupRepository;
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
 * REST controller for managing InstallmentGroup.
 */
@RestController
@RequestMapping("/api")
public class InstallmentGroupResource {

    private final Logger log = LoggerFactory.getLogger(InstallmentGroupResource.class);

    private static final String ENTITY_NAME = "installmentGroup";
        
    private final InstallmentGroupRepository installmentGroupRepository;

    public InstallmentGroupResource(InstallmentGroupRepository installmentGroupRepository) {
        this.installmentGroupRepository = installmentGroupRepository;
    }

    /**
     * POST  /installment-groups : Create a new installmentGroup.
     *
     * @param installmentGroup the installmentGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new installmentGroup, or with status 400 (Bad Request) if the installmentGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/installment-groups")
    @Timed
    public ResponseEntity<InstallmentGroup> createInstallmentGroup(@RequestBody InstallmentGroup installmentGroup) throws URISyntaxException {
        log.debug("REST request to save InstallmentGroup : {}", installmentGroup);
        if (installmentGroup.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new installmentGroup cannot already have an ID")).body(null);
        }
        InstallmentGroup result = installmentGroupRepository.save(installmentGroup);
        return ResponseEntity.created(new URI("/api/installment-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /installment-groups : Updates an existing installmentGroup.
     *
     * @param installmentGroup the installmentGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated installmentGroup,
     * or with status 400 (Bad Request) if the installmentGroup is not valid,
     * or with status 500 (Internal Server Error) if the installmentGroup couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/installment-groups")
    @Timed
    public ResponseEntity<InstallmentGroup> updateInstallmentGroup(@RequestBody InstallmentGroup installmentGroup) throws URISyntaxException {
        log.debug("REST request to update InstallmentGroup : {}", installmentGroup);
        if (installmentGroup.getId() == null) {
            return createInstallmentGroup(installmentGroup);
        }
        InstallmentGroup result = installmentGroupRepository.save(installmentGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, installmentGroup.getId().toString()))
            .body(result);
    }

    /**
     * GET  /installment-groups : get all the installmentGroups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of installmentGroups in body
     */
    @GetMapping("/installment-groups")
    @Timed
    public List<InstallmentGroup> getAllInstallmentGroups() {
        log.debug("REST request to get all InstallmentGroups");
        List<InstallmentGroup> installmentGroups = installmentGroupRepository.findAll();
        return installmentGroups;
    }

    /**
     * GET  /installment-groups/:id : get the "id" installmentGroup.
     *
     * @param id the id of the installmentGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the installmentGroup, or with status 404 (Not Found)
     */
    @GetMapping("/installment-groups/{id}")
    @Timed
    public ResponseEntity<InstallmentGroup> getInstallmentGroup(@PathVariable Long id) {
        log.debug("REST request to get InstallmentGroup : {}", id);
        InstallmentGroup installmentGroup = installmentGroupRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(installmentGroup));
    }

    /**
     * DELETE  /installment-groups/:id : delete the "id" installmentGroup.
     *
     * @param id the id of the installmentGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/installment-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstallmentGroup(@PathVariable Long id) {
        log.debug("REST request to delete InstallmentGroup : {}", id);
        installmentGroupRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
