package com.levymoreira.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.levymoreira.service.InstallmentGroupService;
import com.levymoreira.web.rest.util.HeaderUtil;
import com.levymoreira.web.rest.util.PaginationUtil;
import com.levymoreira.service.dto.InstallmentGroupDTO;
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
 * REST controller for managing InstallmentGroup.
 */
@RestController
@RequestMapping("/api")
public class InstallmentGroupResource {

    private final Logger log = LoggerFactory.getLogger(InstallmentGroupResource.class);

    private static final String ENTITY_NAME = "installmentGroup";
        
    private final InstallmentGroupService installmentGroupService;

    public InstallmentGroupResource(InstallmentGroupService installmentGroupService) {
        this.installmentGroupService = installmentGroupService;
    }

    /**
     * POST  /installment-groups : Create a new installmentGroup.
     *
     * @param installmentGroupDTO the installmentGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new installmentGroupDTO, or with status 400 (Bad Request) if the installmentGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/installment-groups")
    @Timed
    public ResponseEntity<InstallmentGroupDTO> createInstallmentGroup(@RequestBody InstallmentGroupDTO installmentGroupDTO) throws URISyntaxException {
        log.debug("REST request to save InstallmentGroup : {}", installmentGroupDTO);
        if (installmentGroupDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new installmentGroup cannot already have an ID")).body(null);
        }
        InstallmentGroupDTO result = installmentGroupService.save(installmentGroupDTO);
        return ResponseEntity.created(new URI("/api/installment-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /installment-groups : Updates an existing installmentGroup.
     *
     * @param installmentGroupDTO the installmentGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated installmentGroupDTO,
     * or with status 400 (Bad Request) if the installmentGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the installmentGroupDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/installment-groups")
    @Timed
    public ResponseEntity<InstallmentGroupDTO> updateInstallmentGroup(@RequestBody InstallmentGroupDTO installmentGroupDTO) throws URISyntaxException {
        log.debug("REST request to update InstallmentGroup : {}", installmentGroupDTO);
        if (installmentGroupDTO.getId() == null) {
            return createInstallmentGroup(installmentGroupDTO);
        }
        InstallmentGroupDTO result = installmentGroupService.save(installmentGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, installmentGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /installment-groups : get all the installmentGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of installmentGroups in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/installment-groups")
    @Timed
    public ResponseEntity<List<InstallmentGroupDTO>> getAllInstallmentGroups(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of InstallmentGroups");
        Page<InstallmentGroupDTO> page = installmentGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/installment-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /installment-groups/:id : get the "id" installmentGroup.
     *
     * @param id the id of the installmentGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the installmentGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/installment-groups/{id}")
    @Timed
    public ResponseEntity<InstallmentGroupDTO> getInstallmentGroup(@PathVariable Long id) {
        log.debug("REST request to get InstallmentGroup : {}", id);
        InstallmentGroupDTO installmentGroupDTO = installmentGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(installmentGroupDTO));
    }

    /**
     * DELETE  /installment-groups/:id : delete the "id" installmentGroup.
     *
     * @param id the id of the installmentGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/installment-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstallmentGroup(@PathVariable Long id) {
        log.debug("REST request to delete InstallmentGroup : {}", id);
        installmentGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
