package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.InstallmentGroupDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity InstallmentGroup and its DTO InstallmentGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstallmentGroupMapper {

    @Mapping(source = "account.id", target = "accountId")
    InstallmentGroupDTO installmentGroupToInstallmentGroupDTO(InstallmentGroup installmentGroup);

    List<InstallmentGroupDTO> installmentGroupsToInstallmentGroupDTOs(List<InstallmentGroup> installmentGroups);

    @Mapping(source = "accountId", target = "account")
    InstallmentGroup installmentGroupDTOToInstallmentGroup(InstallmentGroupDTO installmentGroupDTO);

    List<InstallmentGroup> installmentGroupDTOsToInstallmentGroups(List<InstallmentGroupDTO> installmentGroupDTOs);

    default AccountName accountNameFromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountName accountName = new AccountName();
        accountName.setId(id);
        return accountName;
    }
}
