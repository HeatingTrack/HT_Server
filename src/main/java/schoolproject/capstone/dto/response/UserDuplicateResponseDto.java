package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserDuplicateResponseDto {
    private int state;
    private String message;

    public UserDuplicateResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
