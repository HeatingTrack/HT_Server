package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class PasswordChangeRequestDto {
    private String user_id;
    private String passwd;
    private String new_passwd;
}
