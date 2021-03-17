package com.salav.cardealership.mapper;

import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.DTO.ClientDTO;
import com.salav.cardealership.model.DTO.OrderDTO;
import com.salav.cardealership.model.Order;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Mappings(
            {@Mapping(target = "client.id",source = "client.clientId")})
    public abstract OrderDTO toDto(Order order);
    @InheritInverseConfiguration(name = "toDto")
    public abstract Order fromDto(OrderDTO orderDTO);
    public abstract List<OrderDTO> toDtoList(Collection<Order> orders);
    @InheritInverseConfiguration(name = "toDtoList")
    public abstract List<Order> fromDtoList(Collection<OrderDTO> ordersDto);

    @AfterMapping
    void setFullName(@MappingTarget ClientDTO clientDTO, Client client) {
        clientDTO.setFullName(client.getName()+" "+client.getSurname());
    }
}
