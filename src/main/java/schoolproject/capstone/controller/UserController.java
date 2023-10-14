package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import schoolproject.capstone.dto.request.UserDeleteRequestDto;
import schoolproject.capstone.dto.request.UserDto;
import schoolproject.capstone.dto.response.UserDuplicateResponseDto;
import schoolproject.capstone.dto.response.UserLoginResponseDto;
import schoolproject.capstone.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    @GetMapping("/duplicate/{email}")
    public UserDuplicateResponseDto userEmailDuplicate(@PathVariable("email") String email) {
        return userService.emailDuplicate(email);
    }

    @DeleteMapping("/delete")
    public void userDelete(@RequestBody UserDeleteRequestDto userDeleteRequestDto) {
        userService.deleteUser(userDeleteRequestDto);
    }
}
