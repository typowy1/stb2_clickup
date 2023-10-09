package pl.stb2clickup.tests.e2e;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.stb2clickup.dto.request.CreateTaskRequestDto;
import pl.stb2clickup.dto.response.CreateTaskResponseDto;
import pl.stb2clickup.requests.list.CreateListRequest;
import pl.stb2clickup.requests.space.CreateSpaceRequest;
import pl.stb2clickup.requests.task.CreateTaskRequest;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String spaceName = "SPACE E2E";
    private static String listName = "ZADANIA";
    private static String taskName = "Przetestować clickup";
    private String spaceId;
    private String listId;
    private String taskId;

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

    }

    private String createSpaceStep() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", spaceName);

        final Response responseSpace = CreateSpaceRequest.createSpace(jsonObject);
        assertThat(responseSpace.statusCode()).isEqualTo(200);

        assertThat(responseSpace.jsonPath().getString("name")).isEqualTo(spaceName);

        return responseSpace.jsonPath().getString("id");
    }

    private String createListStep() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", listName);

        final Response responseList = CreateListRequest.createList(jsonObject, spaceId);
        assertThat(responseList.statusCode()).isEqualTo(200);

        assertThat(responseList.jsonPath().getString("name")).isEqualTo(listName);

        return responseList.jsonPath().getString("id");
    }

    private String createTaskStep() {

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name", taskName);
//        jsonObject.put("description", "lalalala");
//        jsonObject.put("status", JSONObject.NULL);
//        jsonObject.put("priority", JSONObject.NULL);
//        jsonObject.put("parent", JSONObject.NULL);
//        jsonObject.put("time_estimate", JSONObject.NULL);
//        jsonObject.put("assignees", JSONObject.NULL);
//        jsonObject.put("archived", false);

        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);
        taskDto.setDescription("lalalala");
        taskDto.setStatus(null);
        taskDto.setPriority(null);
        taskDto.setParent(null);
        taskDto.setTime_estimate(null);
        taskDto.setAssignees(null);
        taskDto.setArchived(false);

        LOGGER.info("Create task response object {}", taskDto);

        CreateTaskResponseDto responseTask = CreateTaskRequest.createTask(taskDto, listId);
        assertThat(responseTask.getName()).isEqualTo(taskName);
        assertThat(responseTask.getDescription()).isEqualTo("lalalala");

        return responseTask.getId();
    }
}
