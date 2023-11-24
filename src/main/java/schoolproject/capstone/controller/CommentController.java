package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schoolproject.capstone.dto.request.CommentModifyRequestDto;
import schoolproject.capstone.dto.request.CommentRequestWriteDto;
import schoolproject.capstone.dto.response.ResponseMessageDto;
import schoolproject.capstone.service.CommentService;

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
}
