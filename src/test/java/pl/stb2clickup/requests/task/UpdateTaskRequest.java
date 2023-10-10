package pl.stb2clickup.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.stb2clickup.requests.BaseRequest;
import pl.stb2clickup.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {

    public static Response updateTask(JSONObject task, String taskId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(task.toString()) //przy dto nie musimy wywoływac metody to string, poniewaz juz zawiera
                .put(ClickUpUrl.getTaskUrl(taskId))
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }
}
