package tqsdemo.carmngmysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import tqsdemo.carmngmysql.car.Car;
import tqsdemo.carmngmysql.car.CarManagerService;
import tqsdemo.carmngmysql.car.CarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * @author wy
 * @date 2021/4/13 14:52
 */

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest_WithMock {

    @InjectMocks
    private CarManagerService managerService;

    @Mock(lenient = true)
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        Car benz = new Car("Benz", "benz_model");
        benz.setCarId(10L);
        Car bwm = new Car( "BWM", "bwm_model");
        Car audi = new Car( "Audi", "audi_model");

        List<Car> cars = Arrays.asList(benz, bwm, audi);

        Mockito.when(carRepository.findByCarId(benz.getCarId())).thenReturn(benz);
        Mockito.when(carRepository.findByCarId(bwm.getCarId())).thenReturn(bwm);
        Mockito.when(carRepository.findByCarId(bwm.getCarId())).thenReturn(audi);
        Mockito.when(carRepository.findByCarId(100L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(cars);

        Car car = new Car("car", "car_model");
        Mockito.when(carRepository.save(car)).thenReturn(car);
    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Long benzId = 10L;
        Optional<Car> car = managerService.getCarDetails(benzId);
        assertThat(car.get().getCarId()).isEqualTo(benzId);
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(benzId);
    }

    @Test
    public void whenInValidId_thenCarShouldNotBeFound() {
        Long invalidId = 100L;
        assertThatNullPointerException().isThrownBy(() -> managerService.getCarDetails(invalidId));
    }


    @Test
    public void given3Cars_whenGetAll_thenReturn3Records() {
        Car benz = new Car("Benz", "benz_model");
        Car bwm = new Car( "BWM", "bwm_model");
        Car audi = new Car( "Audi", "audi_model");

        List<Car> cars = managerService.getAllCars();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(cars).hasSize(3).extracting(Car::getMaker).contains(benz.getMaker(), bwm.getMaker(), audi.getMaker());
    }

    @Test
    public void saveNewCar_thenItShouldBeFound(){
        Car car = new Car("car", "car_model");
        Car saved = managerService.save(car);
        assertThat(saved).isEqualTo(car);
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
}
