package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.BoardDeleteRequestDto;
import schoolproject.capstone.dto.request.BoardUpdateRequestDto;
import schoolproject.capstone.dto.request.BoardWriteRequestDto;
import schoolproject.capstone.dto.response.*;
import schoolproject.capstone.model.Board;
import schoolproject.capstone.repository.BoardRepository;
import schoolproject.capstone.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardWriteResponseDto boardWrite(BoardWriteRequestDto boardWriteRequestDto) {

        Board board = Board.builder()
                .title(boardWriteRequestDto.getTitle())
                .content(boardWriteRequestDto.getContent())
                .user(userRepository.findById(boardWriteRequestDto.getUserId()).get())
                .build();

        boardRepository.save(board);

        return new BoardWriteResponseDto(board.getId(), board.getTitle());
    }

    public Page<BoardListResponseDto> boardList(Pageable pageable) {
        return boardRepository.findAllBoardList(pageable);
    }

    @Transactional
    public BoardUpdateResponseDto boardUpdate(BoardUpdateRequestDto boardUpdateRequestDto) {

        Optional<Board> optionalBoard = boardRepository.findById(boardUpdateRequestDto.getId());

        if (optionalBoard.isEmpty()) {
            log.info("not search board id");
            return new BoardUpdateResponseDto(0, "해당하는 게시글의 아이디가 존재하지 않습니다");
        }

        if (!optionalBoard.get().getUser().getId().equals(boardUpdateRequestDto.getUser_id())) {
            log.info("this board not write : " + boardUpdateRequestDto.getUser_id());
            return new BoardUpdateResponseDto(0, "해당 사용자가 작성한 게시글이 아닙니다.");
        }

        Board findBoard = optionalBoard.get();

        findBoard.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        log.info("fix success : " + findBoard.getId());
        return new BoardUpdateResponseDto(1, "수정이 완료되었습니다. " + findBoard.getId());

    }

    @Transactional
    public BoardDeleteResponseDto boardDelete(BoardDeleteRequestDto boardDeleteRequestDto) {
        Optional<Board> optionalBoard = boardRepository.findById(boardDeleteRequestDto.getId());

        if (optionalBoard.isEmpty()) {
            log.info("exist not board id : " + boardDeleteRequestDto.getId());
            return new BoardDeleteResponseDto(0, "id에 해당하는 게시물이 존재하지 않습니다.");
        }

        Board findBoard = optionalBoard.get();

        if (!boardDeleteRequestDto.getUser_id().equals(findBoard.getUser().getId())) {
            log.info("this board not write : " + boardDeleteRequestDto.getUser_id());
            return new BoardDeleteResponseDto(0, "해당 사용자가 작성한 게시글이 아닙니다.");
        } else {
            log.info("delete board : " + findBoard.getId());
            boardRepository.deleteById(boardDeleteRequestDto.getId());
            return new BoardDeleteResponseDto(1, "게시글이 정상적으로 삭제되었습니다.");
        }
    }

    public Optional<BoardDetailResponseDto> boardDetail(Long num) {
        Optional<BoardDetailResponseDto> findBoardDetail = boardRepository.findDetailBoard(num);

        if (findBoardDetail.isEmpty()) {
            log.info("not found board num");
            return Optional.empty();
        } else {
            return findBoardDetail;
        }
    }
}
