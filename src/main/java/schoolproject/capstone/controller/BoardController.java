package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schoolproject.capstone.dto.response.BoardListResponseDto;
import schoolproject.capstone.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public Page<BoardListResponseDto> boardList(@PageableDefault(size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.boardList(pageable);
    }
}
