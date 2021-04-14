package tqsdemo.carmngmysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqsdemo.carmngmysql.car.Car;
import tqsdemo.carmngmysql.car.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindBezById_thenReturnBez() {
        Car benz = new Car("Benz", "benz_model");
        entityManager.persistAndFlush(benz); //ensure data is persisted at this point

        Car found = carRepository.findByCarId(benz.getCarId());
        assertThat(found).isEqualTo(benz);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(100L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Car benz = new Car("Benz", "benz_model");
        Car bwm = new Car( "BWM", "bwm_model");
        Car audi = new Car( "Audi", "audi_model");

        entityManager.persist(benz);
        entityManager.persist(bwm);
        entityManager.persist(audi);
        entityManager.flush();

        List<Car> cars = carRepository.findAll();

        assertThat(cars).hasSize(3).extracting(Car::getMaker).contains(benz.getMaker(), bwm.getMaker(), audi.getMaker());
    }

}