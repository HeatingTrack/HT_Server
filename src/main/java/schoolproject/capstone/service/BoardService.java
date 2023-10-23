package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.BoardWriteRequestDto;
import schoolproject.capstone.dto.response.BoardListResponseDto;
import schoolproject.capstone.dto.response.BoardWriteResponseDto;
import schoolproject.capstone.model.Board;
import schoolproject.capstone.repository.BoardRepository;
import schoolproject.capstone.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardWriteResponseDto boardWrite(BoardWriteRequestDto boardWriteRequestDto) {

        Long num = boardRepository.createNum();

        if (num == null) {
            num = 1L;
        } else {
            num++;
        }

        Board board = Board.builder()
                .num(num)
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
}
