package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.ClientDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface ClientMapper {

    @Mapping(source = "user.id", target = "userId")
    ClientDTO clientToClientDTO(Client client);

    List<ClientDTO> clientsToClientDTOs(List<Client> clients);

    @Mapping(source = "userId", target = "user")
    Client clientDTOToClient(ClientDTO clientDTO);

    List<Client> clientDTOsToClients(List<ClientDTO> clientDTOs);
}
