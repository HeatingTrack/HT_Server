package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private String id;
    private String email;
    private String password;

    public UserLoginResponseDto(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
