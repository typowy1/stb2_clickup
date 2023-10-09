package pl.stb2clickup.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //przy deserialiazacji bedzie ignorowal pola ktore sa w jsonie ale nie ma w obiekcie javovym
public class CreateTaskStatusResponseDto {

    private String status;
    private String type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CreateTaskStatusResponseDto{" +
                "status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
