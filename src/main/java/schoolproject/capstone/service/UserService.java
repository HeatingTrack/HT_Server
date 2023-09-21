package schoolproject.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolproject.capstone.dto.request.UserDto;
import schoolproject.capstone.dto.response.UserLoginResponseDto;
import schoolproject.capstone.model.User;
import schoolproject.capstone.repository.UserRepository;

import java.util.Optional;

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

        userRepository.save(user);
    }

    public UserLoginResponseDto login(UserDto userDto) {
        User findUser = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 달라요"));

        if (findUser.getPassword().equals(userDto.getPassword())) {
            UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto(findUser.getId(), findUser.getEmail());
            return userLoginResponseDto;
        } else {
            throw new IllegalArgumentException("아이디와 비밀번호를 확인해주세요");
        }
    }
}
