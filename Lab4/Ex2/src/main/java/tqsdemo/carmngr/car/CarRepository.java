package tqsdemo.carmngr.car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author wy
 * @date 2021/4/13 10:52
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByCarId(Long id);

    List<Car> findAll();
}
