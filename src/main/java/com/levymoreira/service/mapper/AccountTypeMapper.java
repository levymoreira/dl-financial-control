package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.AccountTypeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity AccountType and its DTO AccountTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountTypeMapper {

    AccountTypeDTO accountTypeToAccountTypeDTO(AccountType accountType);

    List<AccountTypeDTO> accountTypesToAccountTypeDTOs(List<AccountType> accountTypes);

    AccountType accountTypeDTOToAccountType(AccountTypeDTO accountTypeDTO);

    List<AccountType> accountTypeDTOsToAccountTypes(List<AccountTypeDTO> accountTypeDTOs);
}
