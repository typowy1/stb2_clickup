package pl.stb2clickup.requests.space;

import io.restassured.response.Response;
import pl.stb2clickup.requests.BaseRequest;
import pl.stb2clickup.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {


    public static Response deleteSpace(String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .delete(ClickUpUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

}
