package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String comment_id;
    private String name;
    private String comment;

    public CommentResponseDto(String comment_id, String name, String comment) {
        this.comment_id = comment_id;
        this.name = name;
        this.comment = comment;
    }
}
