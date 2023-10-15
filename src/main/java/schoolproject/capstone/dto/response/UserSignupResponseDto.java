package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserSignupResponseDto {
    private String id;
    private String email;

    public UserSignupResponseDto(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
