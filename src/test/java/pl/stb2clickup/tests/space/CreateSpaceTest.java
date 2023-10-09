package pl.stb2clickup.tests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.stb2clickup.requests.space.CreateSpaceRequest;
import pl.stb2clickup.requests.space.DeleteSpaceRequest;

import static org.assertj.core.api.Assertions.assertThat;

class CreateSpaceTest {

    private static final String SPACE_NAME = "MY SPACE FROM JAVA";
    private static String spaceId;

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final Response response = CreateSpaceRequest.createSpace(space);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(space.get("name"));

        spaceId = response.jsonPath().getString("id");

        Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
