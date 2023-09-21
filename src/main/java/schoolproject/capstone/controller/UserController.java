package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import schoolproject.capstone.dto.request.UserDto;
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
}
