package com.alfredTech.ATMeeet.userController;
import com.alfredTech.ATMeeet.user.User;
import com.alfredTech.ATMeeet.userServices.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {

           userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }
    @PostMapping("/logout")
    public void logout(@RequestParam("email") String email){
        userService.logout(email);
    }
    @GetMapping("/view_All")
    public List<User> viewAll(){
        return userService.viewAllUsers();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception exception){
        return ResponseEntity.status(HttpStatus.OK)
                .body(exception.getMessage());

    }

}
