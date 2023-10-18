package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserDeleteResponseDto {
    private int status;
    private String message;

    public UserDeleteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
