package pl.stb2clickup.tests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.stb2clickup.tests.requests.space.CreateSpaceRequest;

import static org.assertj.core.api.Assertions.assertThat;

class CreateSpaceTest {

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", "MY SPACE FROM JAVA");

        final Response response = CreateSpaceRequest.createSpace(space);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(space.get("name"));
    }
}
