package pl.stb2clickup.url;

public class ClickUpUrl {
//    można tez to trzymać w propertiesach
    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM_ENDPOINT = "/team";
    private static final String SPACE_ENDPOINT = "/space";

    public static String getBaseUrl(){
        return BASE_URL;
    }

    public static String getTeamsUrl(){
        return TEAM_ENDPOINT;
    }

    public static String getTeamUrl(String teamId){
        return getTeamsUrl() + "/" + teamId;
    }

    public static String getSpacesUrl(String teamId){
        return getTeamUrl(teamId) + SPACE_ENDPOINT;
    }
}
