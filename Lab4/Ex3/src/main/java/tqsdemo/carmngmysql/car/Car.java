package tqsdemo.carmngmysql.car;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author wy
 * @date 2021/4/13 10:49
 */
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tqs_Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;
    private String maker;
    private String model;

    public Car(String maker, String model){
        this.maker = maker;
        this.model = model;
    }
}
