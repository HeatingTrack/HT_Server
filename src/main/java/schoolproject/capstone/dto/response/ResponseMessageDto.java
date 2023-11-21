package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class ResponseMessageDto {
    private int state;
    private String message;

    public ResponseMessageDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
