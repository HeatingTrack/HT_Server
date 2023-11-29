package schoolproject.capstone.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponseDto {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String name;
    private String user_id;
    private int type;


    public BoardDetailResponseDto(String id, String title, String content, LocalDateTime createdAt, String name, String user_id, int type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.name = name;
        this.user_id = user_id;
        this.type = type;
    }
}
