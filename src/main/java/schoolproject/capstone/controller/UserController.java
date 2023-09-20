package schoolproject.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import schoolproject.capstone.dto.SignUpDto;
import schoolproject.capstone.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public void signUp(@RequestBody SignUpDto signUpDto) {
        userService.signUp(signUpDto);
    }
}
