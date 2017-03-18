package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.AccountNameDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity AccountName and its DTO AccountNameDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface AccountNameMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "accountType.id", target = "accountTypeId")
    AccountNameDTO accountNameToAccountNameDTO(AccountName accountName);

    List<AccountNameDTO> accountNamesToAccountNameDTOs(List<AccountName> accountNames);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "accountTypeId", target = "accountType")
    AccountName accountNameDTOToAccountName(AccountNameDTO accountNameDTO);

    List<AccountName> accountNameDTOsToAccountNames(List<AccountNameDTO> accountNameDTOs);

    default AccountType accountTypeFromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountType accountType = new AccountType();
        accountType.setId(id);
        return accountType;
    }
}
