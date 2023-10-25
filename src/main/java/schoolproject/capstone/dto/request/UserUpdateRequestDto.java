package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String id;
    private String name;
    private String phone;
    private String password;
}
