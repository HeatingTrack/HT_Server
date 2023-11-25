package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.UserDeleteRequestDto;
import schoolproject.capstone.dto.request.UserLoginRequestDto;
import schoolproject.capstone.dto.request.UserSignupRequestDto;
import schoolproject.capstone.dto.request.UserUpdateRequestDto;
import schoolproject.capstone.dto.response.*;
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
            return Optional.of(new UserLoginResponseDto(findUser.get().getId(), findUser.get().getEmail(), findUser.get().getName(), findUser.get().getPhone(), findUser.get().getType()));
        } else {
            log.info("아이디와 비밀번호가 잘못 입력됨");
            return Optional.empty();
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
    public Optional<UserDeleteResponseDto> deleteUser(UserDeleteRequestDto userDeleteRequestDto) {

        Optional<User> optionalUser = userRepository.findById(userDeleteRequestDto.getId());

        if (optionalUser.isEmpty()) {
            log.info("아이디에 해당하는 회원이 존재하지 않습니다.");
            log.info("아이디 : " + userDeleteRequestDto.getId());
            return Optional.of(new UserDeleteResponseDto(0, "회원탈퇴에 실패하였습니다."));
        }

        User findUser = optionalUser.get();

        if (passwordEncoder.matches(userDeleteRequestDto.getPassword(), findUser.getPassword())) {
            userRepository.deleteById(findUser.getId());
            log.info("회원탈퇴 성공 : " + findUser.getEmail());
            return Optional.of(new UserDeleteResponseDto(1, "회원탈퇴가 성공하였습니다."));
        } else {
            log.info("비밀번호가 일치하지 않습니다.");
            log.info("탈퇴 시도 아이디 : " + findUser.getId());
            return Optional.of(new UserDeleteResponseDto(0, "회원탈퇴에 실패하였습니다."));
        }

    }

    @Transactional
    public Optional<UserUpdateResponseDto> update(UserUpdateRequestDto userUpdateRequestDto) {

        Optional<User> optionalUser = userRepository.findById(userUpdateRequestDto.getId());
        if (optionalUser.isEmpty()) {
            log.info("not founded user by id : " + userUpdateRequestDto.getId());
            return Optional.of(new UserUpdateResponseDto(0, "아이디에 해당하는 회원이 존재하지 않습니다."));
        } else {
            User findUser = optionalUser.get();

            if (passwordEncoder.matches(userUpdateRequestDto.getPassword(), findUser.getPassword())) {
                findUser.updateUser(userUpdateRequestDto.getName(), userUpdateRequestDto.getPhone());

                log.info("change success user profile");
                return Optional.of(new UserUpdateResponseDto(1, "회원정보 수정이 완료되었습니다."));
            } else {
                log.info("not matched password");
                return Optional.of(new UserUpdateResponseDto(0, "비밀번호가 다릅니다."));
            }

        }

    }
}
