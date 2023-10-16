package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.UserDeleteRequestDto;
import schoolproject.capstone.dto.request.UserLoginRequestDto;
import schoolproject.capstone.dto.request.UserSignupRequestDto;
import schoolproject.capstone.dto.response.UserDuplicateResponseDto;
import schoolproject.capstone.dto.response.UserLoginResponseDto;
import schoolproject.capstone.dto.response.UserSignupResponseDto;
import schoolproject.capstone.model.User;
import schoolproject.capstone.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        User user = User.builder()
                .email(userSignupRequestDto.getEmail())
                .password(passwordEncoder.encode(userSignupRequestDto.getPassword()))
                .name(userSignupRequestDto.getName())
                .phone(userSignupRequestDto.getPhone())
                .build();

        if (userRepository.findByEmail(userSignupRequestDto.getEmail()).isEmpty()) {
            userRepository.save(user);
            log.info("회원가입 완료 user : " + user.getEmail());
            return new UserSignupResponseDto(user.getId(), user.getEmail(), user.getName());
        } else {
            log.info("회원가입 실패 user : " + user.getEmail());
            return new UserSignupResponseDto("이미 가입된 이메일입니다.", user.getEmail(), user.getName());
        }
    }

    public Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> findUser = userRepository.findByEmail(userLoginRequestDto.getEmail());

        if (findUser.isEmpty()) {
            log.info("해당하는 이메일이 서버에 존재하지 않음");
            return Optional.empty();
        }

        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), findUser.get().getPassword())) {
            return Optional.of(new UserLoginResponseDto(findUser.get().getId(), findUser.get().getEmail(), findUser.get().getName(), findUser.get().getPhone()));
        } else {
            log.info("아이디와 비밀번호가 잘못 입력됨");
            return Optional.empty();
//            throw new IllegalArgumentException("아이디와 비밀번호를 확인해주세요");
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
