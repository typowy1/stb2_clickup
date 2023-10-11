package pl.stb2clickup.properties;

import java.util.ResourceBundle;

public class ClickUpProperties {

    private static final String TOKEN = "clickup.token"; //nazwa klucza
    private static final String TEAM_ID = "clickup.team.id"; //nazwa klucza
    private static final String SPACE_ENDPOINT = "clickup.space.endpoint"; //nazwa klucza

    public static String getToken() {
        if (getProperty(TOKEN).isEmpty() || getProperty(TOKEN).startsWith("YOUR")) {
            return System.getProperty("TOKEN");//dodane w jeninsie jako zmienne systemowe
        } else {
            return getProperty(TOKEN);
        }
    }

    public static String getTeamId() {
        if (getProperty(TEAM_ID).isEmpty() || getProperty(TEAM_ID).startsWith("YOUR")) {
            return System.getProperty("TEAM_ID"); //dodane w jeninsie jako zmienne systemowe
        } else {
            return getProperty(TOKEN);
        }
    }

    public static String getSpaceEndpoint() {
        return getProperty(SPACE_ENDPOINT);
    }


    private static String getProperty(String key) { // zwróci wartość klucza z properties
        return ResourceBundle.getBundle("clickup").getString(key);// pierw podajemy nazwe properties, potem nazwe klucza z którego chcemy wyciągnąć wartość
    }
}
