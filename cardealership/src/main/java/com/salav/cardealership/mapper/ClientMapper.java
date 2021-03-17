package com.salav.cardealership.mapper;

import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.DTO.ClientDTO;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    @Mapping(target = "id",source = "clientId")
    public abstract ClientDTO toDto(Client client);
    @InheritInverseConfiguration(name = "toDto")
    public abstract Client fromDto(ClientDTO dto);

    public abstract List<ClientDTO> toClientDto(Collection<Client> clients);
    @InheritInverseConfiguration(name = "toClientDto")
    public abstract List<Client> fromClientDto(Collection<ClientDTO> clientsDto);

    @AfterMapping
    void setFullName(@MappingTarget ClientDTO clientDTO,Client client) {
        clientDTO.setFullName(client.getName()+" "+client.getSurname());
    }
}
