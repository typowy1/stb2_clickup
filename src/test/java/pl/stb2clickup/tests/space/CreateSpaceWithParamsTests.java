package pl.stb2clickup.tests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.stb2clickup.requests.space.CreateSpaceRequest;
import pl.stb2clickup.requests.space.DeleteSpaceRequest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

 class CreateSpaceWithParamsTests {
    private static String spaceId;

    @ParameterizedTest(name = "Create space with space name: {0}")
    @DisplayName("Create space with valid space name")
    @MethodSource("createSpaceData")
    void createSpaceTest(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final Response response = CreateSpaceRequest.createSpace(space);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("name")).isEqualTo(space.get("name"));

        spaceId = response.jsonPath().getString("id");

        Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    private static Stream<Arguments> createSpaceData(){
        return Stream.of(
                Arguments.of("TEST SPACE"),
                Arguments.of("123"),
                Arguments.of("*"),
                Arguments.of("&")
        );
    }
}
