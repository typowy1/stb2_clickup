package pl.stb2clickup.tests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.stb2clickup.requests.space.CreateSpaceRequest;
import pl.stb2clickup.requests.space.DeleteSpaceRequest;
import pl.stb2clickup.requests.space.UpdateSpaceRequest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CreateSpaceTest {
//    struktura projektu
//    main/java/webservis/dto/endpoint
//    main/java/webservis/properties/endpoint
//    main/java/webservis/url/endpoint

//    test/java/webservis/requests/endpoint
//    test/java/webservis/requests/endpoint
//    test/java/BaseRequest

//      test/java/webservis/tests/endpoint
//      test/java/webservis/tests/e2e


    private static final String SPACE_NAME = "MY SPACE FROM JAVA";
    private static final String NEW_SPACE_NAME = "My new space name";
    private static String spaceId;

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final Response response = CreateSpaceRequest.createSpace(space);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(space.get("name"));

        spaceId = response.jsonPath().getString("id");

        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("space_id", spaceId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "My new space name");

        Response updateResponse = UpdateSpaceRequest.updateSpace(jsonObject, pathParam);
        assertThat(updateResponse.statusCode()).isEqualTo(200);
        assertThat(updateResponse.jsonPath().getString("name")).isEqualTo(NEW_SPACE_NAME);

        Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
