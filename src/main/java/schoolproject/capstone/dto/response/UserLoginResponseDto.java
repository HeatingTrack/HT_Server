package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private String id;
    private String email;
    private String name;
    private String phone;

    public UserLoginResponseDto(String id, String email, String name, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
