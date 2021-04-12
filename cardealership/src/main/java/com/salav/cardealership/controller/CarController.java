package com.salav.cardealership.controller;

import com.salav.cardealership.model.Car;
import com.salav.cardealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/p={pageNumber}")
    public ResponseEntity<List<Car>> findPaginated(@PathVariable(value = "pageNumber") int pageN) {
        int pageS = 5;
        Page<Car> page = carService.findPaginatedCars(pageN, pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add("pageMax", String.valueOf(page.getTotalPages()));
        headers.add("TotalItems", String.valueOf(page.getTotalElements()));
        List<Car> listCars = page.getContent();
        return new ResponseEntity<>(listCars, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/nm={name}/p={pageNumber}")
    public ResponseEntity<List<Car>> findPaginatedCarsByName(@PathVariable (value = "pageNumber") int pageN, @PathVariable (value = "name") String name) {
        int pageS = 5;
        Page<Car> page = carService.findPaginatedCarsByName(pageN, pageS, name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("pageMax", String.valueOf(page.getTotalPages()));
        headers.add("TotalItems", String.valueOf(page.getTotalElements()));
        List<Car> listCars = page.getContent();
        return new ResponseEntity<>(listCars, headers, HttpStatus.OK);
    }

    @GetMapping("/nm={name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Car>> findAllCarsByName(@PathVariable (value = "name") String name) {
        List<Car> listCars = carService.findAllByName(name);
        return new ResponseEntity<>(listCars,HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        Car newCar = carService.updateCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Car> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
