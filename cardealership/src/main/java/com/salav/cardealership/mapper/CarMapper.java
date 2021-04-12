package com.salav.cardealership.mapper;

import com.salav.cardealership.model.Car;
import com.salav.cardealership.model.dto.CarDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "id", source = "carId")
    CarDTO toDto(Car car);

    @InheritInverseConfiguration(name = "toDto")
    Car fromDto(CarDTO dto);

    List<CarDTO> toCarDto(Collection<Car> cars);

    @InheritInverseConfiguration(name = "toClientDto")
    List<Car> fromCarDto(Collection<CarDTO> carsDto);
}
