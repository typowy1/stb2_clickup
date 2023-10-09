package pl.stb2clickup.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.stb2clickup.properties.ClickUpProperties;
import pl.stb2clickup.requests.BaseRequest;
import pl.stb2clickup.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {

    public static Response createSpace(JSONObject body) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(body.toString())
                .post(ClickUpUrl.getSpacesUrl(ClickUpProperties.getTeamId()))
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }
}
