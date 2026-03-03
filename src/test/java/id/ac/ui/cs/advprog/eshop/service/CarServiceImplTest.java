package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        carService = new CarServiceImpl();
        ReflectionTestUtils.setField(carService, "carRepository", carRepository);
    }

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarName("TestCar");

        when(carRepository.create(car)).thenReturn(car);

        Car result = carService.create(car);

        verify(carRepository).create(car);
        assertEquals("TestCar", result.getCarName());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        car1.setCarName("Car1");
        Car car2 = new Car();
        car2.setCarName("Car2");
        List<Car> carList = List.of(car1, car2);
        Iterator<Car> iterator = carList.iterator();

        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();

        verify(carRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("FoundCar");

        when(carRepository.findById("car-123")).thenReturn(car);

        Car result = carService.findById("car-123");

        verify(carRepository).findById("car-123");
        assertEquals("FoundCar", result.getCarName());
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarId("car-456");

        carService.update("car-456", car);

        verify(carRepository).update("car-456", car);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById("car-789");

        verify(carRepository).delete("car-789");
    }
}
