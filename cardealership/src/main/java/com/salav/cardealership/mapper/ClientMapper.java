package com.salav.cardealership.mapper;

import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.dto.ClientDTO;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "id",source = "clientId")
    ClientDTO toDto(Client client);
    @InheritInverseConfiguration(name = "toDto")
    Client fromDto(ClientDTO dto);

    List<ClientDTO> toClientDto(Collection<Client> clients);
    @InheritInverseConfiguration(name = "toClientDto")
    List<Client> fromClientDto(Collection<ClientDTO> clientsDto);

    @AfterMapping
    default void setFullName(@MappingTarget ClientDTO clientDTO, Client client) {
        clientDTO.setFullName(client.getSurname()+" "+client.getName());
    }
    @BeforeMapping
    default void setNameAndSurname(@MappingTarget Client client, ClientDTO clientDTO) {
        String[] temp = clientDTO.getFullName().split(" ");
        client.setSurname(temp[0]);
        client.setName(temp[1]);
    }
}
