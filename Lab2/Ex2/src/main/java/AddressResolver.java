import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author wy
 * @date 2021/3/26 9:49
 */
public class AddressResolver {
    private TqsHttpClient httpClient;
    private URIBuilder uriBuilder;

    public Address findAddressForLocation(double latitude, double longitude) throws URISyntaxException, IOException, ParseException {
        if (latitude < -90 || latitude > 90 || longitude > 180 || latitude < -180)
            throw new BadArrayIndex();

        uriBuilder = new URIBuilder("http://open.mapquestapi.com/geocoding/v1/reverse");
        uriBuilder.addParameter("key", "uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ");
        uriBuilder.addParameter("location", (new Formatter()).format(Locale.US, "%.6f,%.6f", latitude, longitude).toString());
        uriBuilder.addParameter("includeRoadMetadata", "true");

        System.err.println(" url is --> " + uriBuilder.build().toString() + " <--");

        String response = this.httpClient.get(uriBuilder.build().toString());

        System.out.println("JSON is: >" + response + "<");

        // get parts from response till reaching the address
        JSONObject obj = (JSONObject) new JSONParser().parse(response);
        obj = (JSONObject) ((JSONArray) obj.get("results")).get(0);
        JSONObject address = (JSONObject) ((JSONArray) obj.get("locations")).get(0);

        String road = (String) address.get("street");
        String city = (String) address.get("adminArea5");
        String state = (String) address.get("adminArea3");
        String zip = (String) address.get("postalCode");
        return new Address(road, city, state, zip, null);

    }

    public TqsHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(TqsHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public URIBuilder getUriBuilder() {
        return uriBuilder;
    }

    public void setUriBuilder(URIBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }
}
