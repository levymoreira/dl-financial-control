package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.TransactionDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Transaction and its DTO TransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface TransactionMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "installmentGroup.id", target = "installmentGroupId")
    @Mapping(source = "creditCardInvoice.id", target = "creditCardInvoiceId")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "source.id", target = "sourceId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "account.id", target = "accountId")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    List<TransactionDTO> transactionsToTransactionDTOs(List<Transaction> transactions);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "installmentGroupId", target = "installmentGroup")
    @Mapping(source = "creditCardInvoiceId", target = "creditCardInvoice")
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "sourceId", target = "source")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "accountId", target = "account")
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);

    List<Transaction> transactionDTOsToTransactions(List<TransactionDTO> transactionDTOs);

    default InstallmentGroup installmentGroupFromId(Long id) {
        if (id == null) {
            return null;
        }
        InstallmentGroup installmentGroup = new InstallmentGroup();
        installmentGroup.setId(id);
        return installmentGroup;
    }

    default CreditCardInvoice creditCardInvoiceFromId(Long id) {
        if (id == null) {
            return null;
        }
        CreditCardInvoice creditCardInvoice = new CreditCardInvoice();
        creditCardInvoice.setId(id);
        return creditCardInvoice;
    }

    default Transaction transactionFromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }

    default Category categoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }

    default Client clientFromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }

    default AccountName accountNameFromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountName accountName = new AccountName();
        accountName.setId(id);
        return accountName;
    }
}
