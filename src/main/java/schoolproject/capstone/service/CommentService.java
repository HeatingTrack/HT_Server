package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.CommentModifyRequestDto;
import schoolproject.capstone.dto.request.CommentRequestWriteDto;
import schoolproject.capstone.dto.response.ResponseMessageDto;
import schoolproject.capstone.model.Board;
import schoolproject.capstone.model.Comment;
import schoolproject.capstone.model.User;
import schoolproject.capstone.repository.BoardRepository;
import schoolproject.capstone.repository.CommentRepository;
import schoolproject.capstone.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Optional<ResponseMessageDto> write(CommentRequestWriteDto commentRequestWriteDto) {
        Optional<Board> optionalBoard = boardRepository.findById(commentRequestWriteDto.getBoard_id());
        Optional<User> optionalUser = userRepository.findById(commentRequestWriteDto.getUser_id());
        Board board;
        User user;

        if (optionalBoard.isEmpty()) {
            return Optional.of(new ResponseMessageDto(0, "게시판 아이디에 해당하는 게시글이 존재하지 않습니다."));
        } else {
            board = optionalBoard.get();
        }

        if (optionalUser.isEmpty()) {
            return Optional.of(new ResponseMessageDto(0, "회원 아이디에 해당하는 회원이 존재하지 않습니다."));
        } else {
            user = optionalUser.get();
        }

        Comment comment = Comment.builder()
                .comment(commentRequestWriteDto.getComment())
                .user(user)
                .board(board)
                .build();

        commentRepository.save(comment);
        return Optional.of(new ResponseMessageDto(1, "댓글 작성이 성공하였습니다."));
    }


    @Transactional
    public Optional<ResponseMessageDto> modify(CommentModifyRequestDto commentModifyRequestDto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentModifyRequestDto.getComment_id());
        if (optionalComment.isEmpty()) {
            return Optional.of(new ResponseMessageDto(0, "아이디에 해당하는 댓글이 존재하지 않습니다."));
        }
        Comment comment = optionalComment.get();

        if (!comment.getUser().getId().equals(commentModifyRequestDto.getUser_id())) {
            return Optional.of(new ResponseMessageDto(0, "댓글 수정은 댓글 작성자만 가능합니다."));
        }

        comment.modify(commentModifyRequestDto.getComment());
        return Optional.of(new ResponseMessageDto(1, "댓글 수정이 완료되었습니다."));
    }
}
