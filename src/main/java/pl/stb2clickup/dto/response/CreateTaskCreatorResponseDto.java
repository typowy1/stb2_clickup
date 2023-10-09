package pl.stb2clickup.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //przy deserialiazacji bedzie ignorowal pola ktore sa w jsonie ale nie ma w obiekcie javovym
public class CreateTaskCreatorResponseDto {
    @JsonProperty("username") //w jason jet ta nazwa username nie camelCase, dziek temu decerializuje, tak by wywalil blad?"
    private String userName;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateTaskCreatorResponseDto{" +
                "username='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
