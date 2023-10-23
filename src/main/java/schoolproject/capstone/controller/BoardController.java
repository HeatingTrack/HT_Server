package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import schoolproject.capstone.dto.request.BoardDeleteRequestDto;
import schoolproject.capstone.dto.request.BoardUpdateRequestDto;
import schoolproject.capstone.dto.request.BoardWriteRequestDto;
import schoolproject.capstone.dto.response.BoardDeleteResponseDto;
import schoolproject.capstone.dto.response.BoardListResponseDto;
import schoolproject.capstone.dto.response.BoardUpdateResponseDto;
import schoolproject.capstone.dto.response.BoardWriteResponseDto;
import schoolproject.capstone.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public BoardWriteResponseDto boardWrite(@RequestBody BoardWriteRequestDto boardWriteRequestDto) {
        return boardService.boardWrite(boardWriteRequestDto);
    }

    @PostMapping("/update")
    public BoardUpdateResponseDto boardUpdate(@RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.boardUpdate(boardUpdateRequestDto);
    }

    @GetMapping("/list")
    public Page<BoardListResponseDto> boardList(@PageableDefault(size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.boardList(pageable);
    }

    @DeleteMapping("/delete")
    public BoardDeleteResponseDto boardDelete(@RequestBody BoardDeleteRequestDto boardDeleteRequestDto) {
        return boardService.boardDelete(boardDeleteRequestDto);
    }
}
