package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class PasswordChangeRequestDto {
    private String id;
    private String passwd;
    private String new_passwd;
}
