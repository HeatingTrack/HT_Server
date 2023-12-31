package schoolproject.capstone.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {
    private Long num;
    private String title;
    private String userName;
    private LocalDateTime createdAt;

    public BoardListResponseDto(Long num, String title, String userName, LocalDateTime createdAt) {
        this.num = num;
        this.title = title;
        this.userName = userName;
        this.createdAt = createdAt;
    }
}
