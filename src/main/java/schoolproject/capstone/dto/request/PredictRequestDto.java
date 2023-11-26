package schoolproject.capstone.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PredictRequestDto {
    private int pre_month;
    private List<PredictRequestYear> user_input;
}
