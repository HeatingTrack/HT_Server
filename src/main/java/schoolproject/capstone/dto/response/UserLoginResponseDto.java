package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private String id;
    private String email;
    private String name;
    private String phone;
    private int type;

    public UserLoginResponseDto(String id, String email, String name, String phone, int type) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }
}
