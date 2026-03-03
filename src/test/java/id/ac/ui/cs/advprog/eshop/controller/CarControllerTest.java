package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    private CarController controller;

    @BeforeEach
    void setUp() {
        controller = new CarController();
        ReflectionTestUtils.setField(controller, "carService", carService);
    }

    @Test
    void createCarPageAddsCarAndReturnsView() {
        Model model = new ExtendedModelMap();

        String viewName = controller.createCarPage(model);

        assertEquals("CreateCar", viewName);
        assertEquals(Car.class, model.getAttribute("car").getClass());
    }

    @Test
    void createCarPostCallsServiceAndRedirects() {
        Car car = new Car();
        Model model = new ExtendedModelMap();

        String viewName = controller.createCarPost(car, model);

        verify(carService).create(car);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void carListPageAddsAllCarsAndReturnsView() {
        Car car = new Car();
        List<Car> cars = List.of(car);
        when(carService.findAll()).thenReturn(cars);
        Model model = new ExtendedModelMap();

        String viewName = controller.carListPage(model);

        verify(carService).findAll();
        assertEquals("CarList", viewName);
        assertEquals(cars, model.getAttribute("cars"));
    }

    @Test
    void editCarPageReturnsEditViewWhenCarExists() {
        Car car = new Car();
        car.setCarId("car-1");
        when(carService.findById("car-1")).thenReturn(car);
        Model model = new ExtendedModelMap();

        String viewName = controller.editCarPage("car-1", model);

        verify(carService).findById("car-1");
        assertEquals("EditCar", viewName);
        assertEquals(car, model.getAttribute("car"));
    }

    @Test
    void editCarPostCallsServiceAndRedirects() {
        Car car = new Car();
        car.setCarId("car-2");
        Model model = new ExtendedModelMap();

        String viewName = controller.editCarPost(car, model);

        verify(carService).update("car-2", car);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void deleteCarCallsServiceAndRedirects() {
        String viewName = controller.deleteCar("car-3");

        verify(carService).deleteCarById("car-3");
        assertEquals("redirect:listCar", viewName);
    }
}
