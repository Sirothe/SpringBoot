package com.salav.cardealership.service;

import com.salav.cardealership.exception.ElementNotFoundException;
import com.salav.cardealership.model.Car;
import com.salav.cardealership.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Page<Car> findPaginatedCars(int pageN, int pageS) {
        Pageable pageable = PageRequest.of(pageN-1,pageS);
        return this.carRepo.findAll(pageable);
    }

    public Car addCar(Car car) {
        return carRepo.save(car);
    }

    public Car updateCar(Car car) {
        return carRepo.save(car);
    }

    public Car findCarById(Long id) {
        return carRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("Car by id"+ id + "was not found"));
    }

    public Page<Car> findPaginatedCarsByName(int pageN,int pageS,String name) {
        Pageable pageable = PageRequest.of(pageN-1,pageS);
        return carRepo.findByNameContains(name,pageable);
    }

    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }

}
