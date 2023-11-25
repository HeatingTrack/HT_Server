package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class CommentDeleteRequestDto {
    private String comment_id;
    private String user_id;
}
