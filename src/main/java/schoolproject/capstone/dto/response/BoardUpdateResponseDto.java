package schoolproject.capstone.dto.response;

import lombok.Getter;

@Getter
public class BoardUpdateResponseDto {
    private int state;
    private String message;

    public BoardUpdateResponseDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
