package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import schoolproject.capstone.dto.request.UserDeleteRequestDto;
import schoolproject.capstone.dto.request.UserLoginRequestDto;
import schoolproject.capstone.dto.request.UserSignupRequestDto;
import schoolproject.capstone.dto.request.UserUpdateRequestDto;
import schoolproject.capstone.dto.response.*;
import schoolproject.capstone.service.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserSignupResponseDto signUp(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return userService.signUp(userSignupRequestDto);
    }

    @PostMapping("/login")
    public Optional<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PostMapping("/update")
    public Optional<UserUpdateResponseDto> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.update(userUpdateRequestDto);
    }

    @GetMapping("/duplicate/{email}")
    public UserDuplicateResponseDto userEmailDuplicate(@PathVariable("email") String email) {

        return userService.emailDuplicate(email);
    }

    @DeleteMapping("/delete")
    public Optional<UserDeleteResponseDto> userDelete(@RequestBody UserDeleteRequestDto userDeleteRequestDto) {
        return userService.deleteUser(userDeleteRequestDto);
    }
}
