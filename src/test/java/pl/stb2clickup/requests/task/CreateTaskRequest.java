package pl.stb2clickup.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.stb2clickup.dto.request.CreateTaskRequestDto;
import pl.stb2clickup.dto.response.CreateTaskResponseDto;
import pl.stb2clickup.requests.BaseRequest;
import pl.stb2clickup.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTask(JSONObject body, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(body.toString())
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

//    tu zrobimy deserializacje czy li zamiast typu zwracanego response zwrócimy obiekt javovy CreateTaskResponseDto
    public static CreateTaskResponseDto createTask(CreateTaskRequestDto task, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(task) //przy dto nie musimy wywoływac metody to string, poniewaz juz zawiera
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .statusCode(200) //tu zwróćmy status cod bo po deserializacji on nam znicknie i nie bedzie do niego dostepu
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response()
                .as(CreateTaskResponseDto.class);
    }

    public static Response createTask(Object object, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(object) //przy dto nie musimy wywoływac metody to string, poniewaz juz zawiera
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }
}
