package tqsdemo.carmngr;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqsdemo.carmngr.car.Car;
import tqsdemo.carmngr.car.CarManagerService;
import tqsdemo.carmngr.car.CarRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author wy
 * @date 2021/4/14 15:16
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CarmngrApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CarControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() throws IOException, Exception {
        Car benz = new Car("Benz", "benz_model");

        mvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(benz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Benz")));

    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() throws Exception {
        Car benz = new Car("Benz", "benz_model");
        Car bwm = new Car( "BWM", "bwm_model");
        Car audi = new Car( "Audi", "audi_model");

        repository.save(benz);
        repository.save(bwm);
        repository.save(audi);
        repository.flush();

        mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].maker", is("Benz")))
                .andExpect(jsonPath("$[1].maker", is("BWM")))
                .andExpect(jsonPath("$[2].maker", is("Audi")));
    }

    @Test
    public void givenCars_whenGetOneByOne_thenReturnEachOfThem() throws Exception {
        Car benz = new Car("Benz", "benz_model");
        Car bwm = new Car( "BWM", "bwm_model");
        Car audi = new Car( "Audi", "audi_model");

        repository.save(benz);
        repository.save(bwm);
        repository.save(audi);
        repository.flush();

        List<Car> cars = Arrays.asList(benz, bwm, audi);

        for (Car car : cars) {
            mvc.perform(get("/cars/" + car.getCarId()).contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isFound())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.maker", is(car.getMaker())));
         }

    }
}
