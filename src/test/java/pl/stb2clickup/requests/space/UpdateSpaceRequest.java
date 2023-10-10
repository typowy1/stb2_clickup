package pl.stb2clickup.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.stb2clickup.properties.ClickUpProperties;
import pl.stb2clickup.requests.BaseRequest;
import pl.stb2clickup.url.ClickUpUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateSpaceRequest {
//  path i query params dodawane
    public static Response updateSpace(JSONObject body, Map pathParams) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .pathParams(pathParams)
                .body(body.toString())
                .put(ClickUpProperties.getSpaceEndpoint())//z propertiesów brany jest endpoint, głowny url w spec jest
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    public static Response updateSpace(Object body, Map pathParams) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .pathParams(pathParams)
                .body(body) //przy obiekcie javowym nie trzeba toString
                .put(ClickUpProperties.getSpaceEndpoint())
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }
}
