package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class BoardWriteResponseDto {
    private String id;
    private String title;

    public BoardWriteResponseDto(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
