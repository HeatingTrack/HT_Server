package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schoolproject.capstone.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
}
