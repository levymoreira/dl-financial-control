package com.levymoreira.service;

import com.levymoreira.domain.AccountType;
import com.levymoreira.repository.AccountTypeRepository;
import com.levymoreira.service.dto.AccountTypeDTO;
import com.levymoreira.service.mapper.AccountTypeMapper;
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
 * Service Implementation for managing AccountType.
 */
@Service
@Transactional
public class AccountTypeService {

    private final Logger log = LoggerFactory.getLogger(AccountTypeService.class);
    
    private final AccountTypeRepository accountTypeRepository;

    private final AccountTypeMapper accountTypeMapper;

    public AccountTypeService(AccountTypeRepository accountTypeRepository, AccountTypeMapper accountTypeMapper) {
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeMapper = accountTypeMapper;
    }

    /**
     * Save a accountType.
     *
     * @param accountTypeDTO the entity to save
     * @return the persisted entity
     */
    public AccountTypeDTO save(AccountTypeDTO accountTypeDTO) {
        log.debug("Request to save AccountType : {}", accountTypeDTO);
        AccountType accountType = accountTypeMapper.accountTypeDTOToAccountType(accountTypeDTO);
        accountType = accountTypeRepository.save(accountType);
        AccountTypeDTO result = accountTypeMapper.accountTypeToAccountTypeDTO(accountType);
        return result;
    }

    /**
     *  Get all the accountTypes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccountTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountTypes");
        Page<AccountType> result = accountTypeRepository.findAll(pageable);
        return result.map(accountType -> accountTypeMapper.accountTypeToAccountTypeDTO(accountType));
    }

    /**
     *  Get one accountType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AccountTypeDTO findOne(Long id) {
        log.debug("Request to get AccountType : {}", id);
        AccountType accountType = accountTypeRepository.findOne(id);
        AccountTypeDTO accountTypeDTO = accountTypeMapper.accountTypeToAccountTypeDTO(accountType);
        return accountTypeDTO;
    }

    /**
     *  Delete the  accountType by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccountType : {}", id);
        accountTypeRepository.delete(id);
    }
}
