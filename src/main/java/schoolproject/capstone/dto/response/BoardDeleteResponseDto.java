package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class BoardDeleteResponseDto {
    private int state;
    private String message;

    public BoardDeleteResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
