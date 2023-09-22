package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserDuplicateResponseDto {
    private int status;
    private String message;

    public UserDuplicateResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
