package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import schoolproject.capstone.dto.request.UserDto;
import schoolproject.capstone.dto.response.UserDuplicateResponseDto;
import schoolproject.capstone.dto.response.UserLoginResponseDto;
import schoolproject.capstone.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public void signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
    }

    @PostMapping("/user/login")
    public UserLoginResponseDto login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    @GetMapping("/user/duplicate/{email}")
    public UserDuplicateResponseDto UserEmailDuplicate(@PathVariable("email") String email) {
        return userService.emailDuplicate(email);
    }
}
