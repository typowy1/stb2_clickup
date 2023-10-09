package pl.stb2clickup.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) //prywatny konstruktor
public class ClickUpUrl {
    //    można tez to trzymać w propertiesach
    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM_ENDPOINT = "/team";
    private static final String SPACE_ENDPOINT = "/space";
    private static final String LIST_ENDPOINT = "/list";
    private static final String TASK_ENDPOINT = "/task";


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getTeamsUrl() {
        return TEAM_ENDPOINT;
    }

    public static String getTeamUrl(String teamId) {
        return getTeamsUrl() + "/" + teamId;
    }

    public static String getSpacesUrl(String teamId) {
        return getTeamUrl(teamId) + SPACE_ENDPOINT;
    }

    public static String getSpaceUrl(String spaceId) {
        return SPACE_ENDPOINT + "/" + spaceId;
    }

    public static String getListsUrl(String spaceId) {
        return getSpaceUrl(spaceId) + LIST_ENDPOINT;
    }

    public static String getListUrl(String listId) {
        return LIST_ENDPOINT + "/" + listId;
    }

    public static String getTasksUrl(String listId) {
        return getListUrl(listId) + TASK_ENDPOINT;
    }

    public static String getTaskUrl(String taskId) {
        return TASK_ENDPOINT + "/" + taskId;
    }

}
