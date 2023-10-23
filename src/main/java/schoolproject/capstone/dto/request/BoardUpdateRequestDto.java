package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class BoardUpdateRequestDto {
    private String id;
    private String title;
    private String content;
    private String user_id;
}
