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
import schoolproject.capstone.dto.response.*;
import schoolproject.capstone.service.BoardService;

import java.util.Optional;

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

    @GetMapping("/list/{name}")
    public Page<BoardListResponseDto> boardListFindByUserName(@PathVariable("name") String name,
                                                              @PageableDefault(size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.boardListFindByUserName(name, pageable);

    }

    @GetMapping("/{num}")
    public Optional<BoardDetailResponseDto> boardDetail(@PathVariable("num") Long num) {
        return boardService.boardDetail(num);
    }

    @DeleteMapping("/delete")
    public BoardDeleteResponseDto boardDelete(@RequestBody BoardDeleteRequestDto boardDeleteRequestDto) {
        return boardService.boardDelete(boardDeleteRequestDto);
    }
}
