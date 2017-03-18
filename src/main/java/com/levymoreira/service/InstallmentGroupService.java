package com.levymoreira.service;

import com.levymoreira.domain.InstallmentGroup;
import com.levymoreira.repository.InstallmentGroupRepository;
import com.levymoreira.service.dto.InstallmentGroupDTO;
import com.levymoreira.service.mapper.InstallmentGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing InstallmentGroup.
 */
@Service
@Transactional
public class InstallmentGroupService {

    private final Logger log = LoggerFactory.getLogger(InstallmentGroupService.class);
    
    private final InstallmentGroupRepository installmentGroupRepository;

    private final InstallmentGroupMapper installmentGroupMapper;

    public InstallmentGroupService(InstallmentGroupRepository installmentGroupRepository, InstallmentGroupMapper installmentGroupMapper) {
        this.installmentGroupRepository = installmentGroupRepository;
        this.installmentGroupMapper = installmentGroupMapper;
    }

    /**
     * Save a installmentGroup.
     *
     * @param installmentGroupDTO the entity to save
     * @return the persisted entity
     */
    public InstallmentGroupDTO save(InstallmentGroupDTO installmentGroupDTO) {
        log.debug("Request to save InstallmentGroup : {}", installmentGroupDTO);
        InstallmentGroup installmentGroup = installmentGroupMapper.installmentGroupDTOToInstallmentGroup(installmentGroupDTO);
        installmentGroup = installmentGroupRepository.save(installmentGroup);
        InstallmentGroupDTO result = installmentGroupMapper.installmentGroupToInstallmentGroupDTO(installmentGroup);
        return result;
    }

    /**
     *  Get all the installmentGroups.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<InstallmentGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InstallmentGroups");
        Page<InstallmentGroup> result = installmentGroupRepository.findAll(pageable);
        return result.map(installmentGroup -> installmentGroupMapper.installmentGroupToInstallmentGroupDTO(installmentGroup));
    }

    /**
     *  Get one installmentGroup by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public InstallmentGroupDTO findOne(Long id) {
        log.debug("Request to get InstallmentGroup : {}", id);
        InstallmentGroup installmentGroup = installmentGroupRepository.findOne(id);
        InstallmentGroupDTO installmentGroupDTO = installmentGroupMapper.installmentGroupToInstallmentGroupDTO(installmentGroup);
        return installmentGroupDTO;
    }

    /**
     *  Delete the  installmentGroup by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete InstallmentGroup : {}", id);
        installmentGroupRepository.delete(id);
    }
}
