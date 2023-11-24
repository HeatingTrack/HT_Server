package schoolproject.capstone.dto.request;

import lombok.Getter;

@Getter
public class CommentRequestWriteDto {
    private String comment;
    private String board_id;
    private String user_id;
}
