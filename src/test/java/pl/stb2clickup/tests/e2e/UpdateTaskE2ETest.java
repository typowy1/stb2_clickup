package pl.stb2clickup.tests.e2e;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.stb2clickup.dto.task.request.CreateTaskRequestDto;
import pl.stb2clickup.dto.task.response.CreateTaskResponseDto;
import pl.stb2clickup.requests.list.CreateListRequest;
import pl.stb2clickup.requests.space.CreateSpaceRequest;
import pl.stb2clickup.requests.space.DeleteSpaceRequest;
import pl.stb2clickup.requests.task.CreateTaskRequest;
import pl.stb2clickup.requests.task.UpdateTaskRequest;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String spaceName = "SPACE E2E";
    private static String listName = "ZADANIA";
    private static String taskName = "Przetestować clickup";
    private String spaceId;
    private String listId;
    private String taskId;

    @Step("Test E2E")
    @Test
//    minus tego podejścia jest taki że nie ma widocznych poszczegolnych krokow w run tylko taka metoda updateTaskE2ETest jest widoczna
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
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
//biblioteka jakson służy do zarządzania dto
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);
        taskDto.setDescription("lalalala");
        taskDto.setStatus(null);
        taskDto.setPriority(null);
        taskDto.setParent(null);
        taskDto.setTime_estimate(null);
        taskDto.setAssignees(null);
        taskDto.setArchived(false);

        LOGGER.info("Create task response object {}", taskDto); //w body request i tak zostanie wyświetlone, to nie potrzebne

        CreateTaskResponseDto responseTask = CreateTaskRequest.createTask(taskDto, listId);
        assertThat(responseTask.getName()).isEqualTo(taskName);
        assertThat(responseTask.getDescription()).isEqualTo("lalalala");

        return responseTask.getId();
    }

    private void updateTaskStep() { //przy update często łatwiej utworzyć JSONobject niz robic dto, dlatego ze czesto jest duzo pol
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Zmieniam nazwe zadania");
        jsonObject.put("description", "lalalalaUpdate");


        LOGGER.info("Create task response object {}", jsonObject);

        Response updateTaskResponse = UpdateTaskRequest.updateTask(jsonObject, taskId);
        assertThat(updateTaskResponse.statusCode()).isEqualTo(200);

        assertThat(updateTaskResponse.jsonPath().getString("name")).isEqualTo("Zmieniam nazwe zadania");
        assertThat(updateTaskResponse.jsonPath().getString("description")).isEqualTo("lalalalaUpdate");
    }

    private void closeTaskStep() {
        JSONObject closeTask = new JSONObject();
        closeTask.put("status", "complete");

        Response updateTaskResponse = UpdateTaskRequest.updateTask(closeTask, taskId);
        assertThat(updateTaskResponse.statusCode()).isEqualTo(200);
// obiekcie status jest pole status czyli status.status
        assertThat(updateTaskResponse.jsonPath().getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep(){
        Response response = DeleteSpaceRequest.deleteSpace(spaceId);
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
