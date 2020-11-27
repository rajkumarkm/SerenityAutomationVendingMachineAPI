package uk.co.city.bike;

import static org.slf4j.LoggerFactory.getLogger;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;

public class ApiActions {

    protected static Logger logger = getLogger(ApiActions.class);

    protected static String endpointRoot;

    public ApiActions() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", 4000).
                setParam("http.socket.timeout", 40000).
                setParam("http.connection-manager.timeout", 4000));
    }

    public int getResponseStatusCode() {
        return SerenityRest.then().extract().statusCode();
    }

    public String getResponseBody() {
        return SerenityRest
                .then()
                .extract()
                .response()
                .asString();
    }

    @Step
    public void runHealthcheck() {
        SerenityRest.given()
                .log().all()
                .when()
                .get(endpointRoot + "admin/healthcheck");
    }
}
