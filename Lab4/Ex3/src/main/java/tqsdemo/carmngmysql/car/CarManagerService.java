package tqsdemo.carmngmysql.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author wy
 * @date 2021/4/13 12:05
 */
@Service
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        return Optional.of(carRepository.findByCarId(id));
    }
}
