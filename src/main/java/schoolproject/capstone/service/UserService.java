package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.UserDeleteRequestDto;
import schoolproject.capstone.dto.request.UserDto;
import schoolproject.capstone.dto.response.UserDuplicateResponseDto;
import schoolproject.capstone.dto.response.UserLoginResponseDto;
import schoolproject.capstone.model.User;
import schoolproject.capstone.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        if (userRepository.findByEmail(userDto.getEmail()).isEmpty()) {
            userRepository.save(user);
        } else {
            throw new IllegalStateException("이미 가입되어있는 회원입니다.");
        }
    }

    public UserLoginResponseDto login(UserDto userDto) {
        User findUser = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 달라요"));

        if (findUser.getPassword().equals(userDto.getPassword())) {
            return new UserLoginResponseDto(findUser.getId(), findUser.getEmail());
        } else {
            throw new IllegalArgumentException("아이디와 비밀번호를 확인해주세요");
        }
    }

    public UserDuplicateResponseDto emailDuplicate(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            return new UserDuplicateResponseDto(1, "가입 가능한 이메일입니다.");
        } else {
            return new UserDuplicateResponseDto(0, "이미 가입된 이메일입니다.");
        }
    }

    @Transactional
    public void deleteUser(UserDeleteRequestDto userDeleteRequestDto) {
        User findUser = userRepository.findById(userDeleteRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("아이디에 해당되는 회원이 존재하지 않음"));

        if (userDeleteRequestDto.getPassword().equals(findUser.getPassword())) {
            userRepository.deleteById(findUser.getId());
        } else {
            throw new IllegalStateException("비밀번호가 일치하지 않음");
        }
    }
}
