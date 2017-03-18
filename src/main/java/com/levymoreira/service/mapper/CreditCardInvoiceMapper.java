package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.CreditCardInvoiceDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity CreditCardInvoice and its DTO CreditCardInvoiceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CreditCardInvoiceMapper {

    @Mapping(source = "account.id", target = "accountId")
    CreditCardInvoiceDTO creditCardInvoiceToCreditCardInvoiceDTO(CreditCardInvoice creditCardInvoice);

    List<CreditCardInvoiceDTO> creditCardInvoicesToCreditCardInvoiceDTOs(List<CreditCardInvoice> creditCardInvoices);

    @Mapping(source = "accountId", target = "account")
    CreditCardInvoice creditCardInvoiceDTOToCreditCardInvoice(CreditCardInvoiceDTO creditCardInvoiceDTO);

    List<CreditCardInvoice> creditCardInvoiceDTOsToCreditCardInvoices(List<CreditCardInvoiceDTO> creditCardInvoiceDTOs);

    default AccountName accountNameFromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountName accountName = new AccountName();
        accountName.setId(id);
        return accountName;
    }
}
