package com.levymoreira.service;

import com.levymoreira.domain.CreditCardInvoice;
import com.levymoreira.repository.CreditCardInvoiceRepository;
import com.levymoreira.service.dto.CreditCardInvoiceDTO;
import com.levymoreira.service.mapper.CreditCardInvoiceMapper;
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
 * Service Implementation for managing CreditCardInvoice.
 */
@Service
@Transactional
public class CreditCardInvoiceService {

    private final Logger log = LoggerFactory.getLogger(CreditCardInvoiceService.class);
    
    private final CreditCardInvoiceRepository creditCardInvoiceRepository;

    private final CreditCardInvoiceMapper creditCardInvoiceMapper;

    public CreditCardInvoiceService(CreditCardInvoiceRepository creditCardInvoiceRepository, CreditCardInvoiceMapper creditCardInvoiceMapper) {
        this.creditCardInvoiceRepository = creditCardInvoiceRepository;
        this.creditCardInvoiceMapper = creditCardInvoiceMapper;
    }

    /**
     * Save a creditCardInvoice.
     *
     * @param creditCardInvoiceDTO the entity to save
     * @return the persisted entity
     */
    public CreditCardInvoiceDTO save(CreditCardInvoiceDTO creditCardInvoiceDTO) {
        log.debug("Request to save CreditCardInvoice : {}", creditCardInvoiceDTO);
        CreditCardInvoice creditCardInvoice = creditCardInvoiceMapper.creditCardInvoiceDTOToCreditCardInvoice(creditCardInvoiceDTO);
        creditCardInvoice = creditCardInvoiceRepository.save(creditCardInvoice);
        CreditCardInvoiceDTO result = creditCardInvoiceMapper.creditCardInvoiceToCreditCardInvoiceDTO(creditCardInvoice);
        return result;
    }

    /**
     *  Get all the creditCardInvoices.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CreditCardInvoiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CreditCardInvoices");
        Page<CreditCardInvoice> result = creditCardInvoiceRepository.findAll(pageable);
        return result.map(creditCardInvoice -> creditCardInvoiceMapper.creditCardInvoiceToCreditCardInvoiceDTO(creditCardInvoice));
    }

    /**
     *  Get one creditCardInvoice by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public CreditCardInvoiceDTO findOne(Long id) {
        log.debug("Request to get CreditCardInvoice : {}", id);
        CreditCardInvoice creditCardInvoice = creditCardInvoiceRepository.findOne(id);
        CreditCardInvoiceDTO creditCardInvoiceDTO = creditCardInvoiceMapper.creditCardInvoiceToCreditCardInvoiceDTO(creditCardInvoice);
        return creditCardInvoiceDTO;
    }

    /**
     *  Delete the  creditCardInvoice by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CreditCardInvoice : {}", id);
        creditCardInvoiceRepository.delete(id);
    }
}
