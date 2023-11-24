package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class CommentModifyRequestDto {
    private String comment_id;
    private String comment;
    private String user_id;
}
