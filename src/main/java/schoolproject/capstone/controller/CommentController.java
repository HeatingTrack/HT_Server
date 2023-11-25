package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import schoolproject.capstone.dto.request.CommentDeleteRequestDto;
import schoolproject.capstone.dto.request.CommentModifyRequestDto;
import schoolproject.capstone.dto.request.CommentRequestWriteDto;
import schoolproject.capstone.dto.response.CommentResponseDto;
import schoolproject.capstone.dto.response.ResponseMessageDto;
import schoolproject.capstone.service.CommentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/write")
    public Optional<ResponseMessageDto> writeComment(@RequestBody CommentRequestWriteDto commentRequestWriteDto) {
        return commentService.write(commentRequestWriteDto);
    }

    @PostMapping("/modify")
    public Optional<ResponseMessageDto> modifyComment(@RequestBody CommentModifyRequestDto commentModifyRequestDto) {
        return commentService.modify(commentModifyRequestDto);
    }

    @DeleteMapping("/delete")
    public Optional<ResponseMessageDto> deleteComment(@RequestBody CommentDeleteRequestDto commentDeleteRequestDto) {
        return commentService.delete(commentDeleteRequestDto);
    }

    @GetMapping("/list/{board_id}")
    public List<CommentResponseDto> getCommentList(@PathVariable("board_id") String boardId) {
        return commentService.getComments(boardId);
    }
}
