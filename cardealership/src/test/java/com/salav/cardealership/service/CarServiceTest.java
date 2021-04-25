package com.salav.cardealership.service;

import com.salav.cardealership.model.Car;
import com.salav.cardealership.repo.CarRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepo carRepo;
    private CarService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarService(carRepo);
        when(carRepo.save(any())).thenReturn(new Car("TestName", "TestModel", 1111));
    }

    @Test
    @Disabled
    void findPaginatedCars() {
        // when
        underTest.findPaginatedCars(1, 5);
        // then
        Pageable pageable = PageRequest.of(0, 5);
        verify(carRepo).findAll(pageable);
    }

    @Test
    @Disabled
    void addCar() {
        // given
        Car testCar = new Car("testName", "testModel", 1111);
        // when
        Car savedCar = underTest.addCar(testCar);
        // then
        assertThat(testCar.getName()).isEqualTo(savedCar.getName());
    }

    @Test
    @Disabled
    void findAllByName() {
        //when
        underTest.findAllByName("a");
        //then
        verify(carRepo).findAllByNameContains("a", Sort.by("name"));
    }

    @Test
    @Disabled("not implemented yet findCarById")
    void findCarById() {
    }

    @Test
    @Disabled("not implemented yet findPaginatedCarsByName")
    void findPaginatedCarsByName() {
    }

    @Test
    @Disabled("not implemented yet deleteCar")
    void deleteCar() {
    }
}