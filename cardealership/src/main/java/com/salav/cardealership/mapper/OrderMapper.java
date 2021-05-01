package com.salav.cardealership.mapper;

import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.Order;
import com.salav.cardealership.model.dto.ClientDTO;
import com.salav.cardealership.model.dto.OrderDTO;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "client.id", source = "client.clientId")
    OrderDTO toDto(Order order);

    @InheritInverseConfiguration(name = "toDto")
    Order fromDto(OrderDTO orderDTO);

    List<OrderDTO> toDtoList(Collection<Order> orders);

    @InheritInverseConfiguration(name = "toDtoList")
    List<Order> fromDtoList(Collection<OrderDTO> ordersDto);

    @AfterMapping
    default void setFullName(@MappingTarget ClientDTO clientDTO, Client client) {
        clientDTO.setFullName(client.getName() + " " + client.getSurname());
    }

    @BeforeMapping
    default void setNameAndSurname(@MappingTarget Client client, ClientDTO clientDTO) {
        String[] temp = clientDTO.getFullName().split(" ");
        client.setSurname(temp[0]);
        client.setName(temp[1]);
    }
}
