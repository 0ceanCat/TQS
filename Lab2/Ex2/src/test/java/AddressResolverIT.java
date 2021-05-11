import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author wy
 * @date 2021/3/36 10:08
 */
public class AddressResolverIT {
    private TqsHttpClient httpClient;
    private AddressResolver resolver;

    @BeforeEach
    void init() {
        httpClient = new TqsHttpBasic();

        resolver = new AddressResolver();
        resolver.setHttpClient(httpClient);
    }

    @Test
    void whenGoodAlboiGps_returnAddress() throws ParseException, IOException, URISyntaxException {
        //test
        Address result = resolver.findAddressForLocation(40.640661, -8.656688);

        //return
        assertEquals(result, new Address("Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null));

    }

    @Test
    public void whenBadCoordinates_throwBadArrayIndex() throws IOException {
        // bad coordinates
        assertThrows(BadArrayIndex.class,
                () -> resolver.findAddressForLocation(180, 190));
    }

}
