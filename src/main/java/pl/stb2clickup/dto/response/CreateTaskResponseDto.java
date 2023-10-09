package pl.stb2clickup.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //przy deserialiazacji bedzie ignorowal pola ktore sa w jsonie ale nie ma w obiekcie javovym
public class CreateTaskResponseDto {

    private String id;
    private String name;
    @JsonProperty("text_content") //w jason jet ta nazwa text_contenta nie camelCase, dziek temu decerializuje, tak by wywalil blad?"
    private String textContent;
    private String description;
    private CreateTaskStatusResponseDto status;
    private CreateTaskCreatorResponseDto creator;

}
