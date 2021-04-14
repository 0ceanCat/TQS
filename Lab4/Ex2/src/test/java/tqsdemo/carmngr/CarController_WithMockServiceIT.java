package tqsdemo.carmngr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqsdemo.carmngr.car.Car;
import tqsdemo.carmngr.car.CarController;
import tqsdemo.carmngr.car.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wy
 * @date 2021/4/13 12:17
 */
@WebMvcTest(CarController.class)
public class CarController_WithMockServiceIT {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car benz = new Car("Benz", "benz_model");

        when(service.save(Mockito.any())).thenReturn(benz);

        mvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(benz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Benz")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car benz = new Car( "Benz", "benz_model");
        Car bwm = new Car( "BWM", "bwm_model");
        Car audi = new Car( "Audi", "audi_model");

        given(service.getAllCars()).willReturn(List.of(benz, bwm, audi));

        mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(benz.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(bwm.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(audi.getMaker())));
        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }

    @Test
    public void givenCarId_whenGetCars_thenReturnCar() throws Exception {
        Car benz = new Car( "Benz", "benz_model");

        given(service.getCarDetails(Mockito.any())).willReturn(Optional.of(benz));

        mvc.perform(get("/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.maker", is(benz.getMaker())))
                .andExpect(jsonPath("$.model", is(benz.getModel())));
        verify(service, VerificationModeFactory.times(1)).getCarDetails(Mockito.any());
    }
}
