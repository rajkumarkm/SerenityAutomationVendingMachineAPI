package uk.co.city.bike;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.slf4j.LoggerFactory.getLogger;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;

public class BikeApiActions extends ApiActions {
    private static final Logger LOGGER = getLogger(BikeApiActions.class);

    private static final String PATH = "http://api.citybik.es/v2/networks";

    @Step
    public Response getBike() {
        return SerenityRest.given()
                .urlEncodingEnabled(false)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .log()
                .all()
                .get(PATH);
    }
}
