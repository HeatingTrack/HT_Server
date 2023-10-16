package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private String id;
    private String email;

    public UserLoginResponseDto(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
