package com.levymoreira.service;

import com.levymoreira.domain.AccountName;
import com.levymoreira.repository.AccountNameRepository;
import com.levymoreira.service.dto.AccountNameDTO;
import com.levymoreira.service.mapper.AccountNameMapper;
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
 * Service Implementation for managing AccountName.
 */
@Service
@Transactional
public class AccountNameService {

    private final Logger log = LoggerFactory.getLogger(AccountNameService.class);
    
    private final AccountNameRepository accountNameRepository;

    private final AccountNameMapper accountNameMapper;

    public AccountNameService(AccountNameRepository accountNameRepository, AccountNameMapper accountNameMapper) {
        this.accountNameRepository = accountNameRepository;
        this.accountNameMapper = accountNameMapper;
    }

    /**
     * Save a accountName.
     *
     * @param accountNameDTO the entity to save
     * @return the persisted entity
     */
    public AccountNameDTO save(AccountNameDTO accountNameDTO) {
        log.debug("Request to save AccountName : {}", accountNameDTO);
        AccountName accountName = accountNameMapper.accountNameDTOToAccountName(accountNameDTO);
        accountName = accountNameRepository.save(accountName);
        AccountNameDTO result = accountNameMapper.accountNameToAccountNameDTO(accountName);
        return result;
    }

    /**
     *  Get all the accountNames.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AccountNameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountNames");
        Page<AccountName> result = accountNameRepository.findAll(pageable);
        return result.map(accountName -> accountNameMapper.accountNameToAccountNameDTO(accountName));
    }

    /**
     *  Get one accountName by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AccountNameDTO findOne(Long id) {
        log.debug("Request to get AccountName : {}", id);
        AccountName accountName = accountNameRepository.findOne(id);
        AccountNameDTO accountNameDTO = accountNameMapper.accountNameToAccountNameDTO(accountName);
        return accountNameDTO;
    }

    /**
     *  Delete the  accountName by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AccountName : {}", id);
        accountNameRepository.delete(id);
    }
}
