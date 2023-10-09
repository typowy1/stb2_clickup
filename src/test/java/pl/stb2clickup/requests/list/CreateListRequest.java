package pl.stb2clickup.requests.list;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.stb2clickup.requests.BaseRequest;
import pl.stb2clickup.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createList(JSONObject body, String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(body.toString())
                .post(ClickUpUrl.getListsUrl(spaceId))
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }
}
