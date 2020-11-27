package uk.co.api.runner.stepDefs;

import static com.jayway.jsonpath.JsonPath.parse;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.minidev.json.JSONArray;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import uk.co.city.bike.BikeApiActions;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CityBikeStepDefs {

    @Steps
    BikeApiActions bikeApiActions;

    @Given("^I query the citybike network endpoint$")
    public void i_query_the_citybike_network_endpoint() {
        bikeApiActions.getBike();
    }
    @Then("^I can verify the below details in$")
    public void i_can_verify_the_below_details_in(List<Map<String, String>> data) {
        assertThat(bikeApiActions.getResponseStatusCode(), is(HttpStatus.SC_OK));
        DocumentContext bikeResponseBody = parse(bikeApiActions.getResponseBody());
        Map<String, String> countries = getCountryCodes();
//        JSONArray networks = bikeResponseBody.read("networks");
//        System.out.println(networks.toJSONString());

        for (Map<String, String> e : data) {
            HashMap<String, String> network = (HashMap<String, String>) ((JSONArray)bikeResponseBody.read(format(".[*].location[?(@.city=='%s')]", e.get("City")))).get(0);
            assertThat("Country wrong", network.get("country"), is(countries.get(e.get("Country"))));
            assertThat("Latitude wrong", network.get("latitude"), is(Double.parseDouble(e.get("Latitude"))));
            assertThat("Longitude wrong", network.get("longitude"), is(Double.parseDouble(e.get("Longitude"))));
        }
    }

    private Map<String, String> getCountryCodes(){
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
        return countries;
    }

}
