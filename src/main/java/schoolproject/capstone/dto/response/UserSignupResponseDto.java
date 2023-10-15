package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserSignupResponseDto {
    private String id;
    private String email;
    private String name;

    public UserSignupResponseDto(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
