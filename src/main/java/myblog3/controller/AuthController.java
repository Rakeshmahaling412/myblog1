package myblog3.controller;

import lombok.AllArgsConstructor;
import myblog3.payload.LoginDto;
import myblog3.payload.SignUpDto;
import myblog3.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignUpDto signUpDto){
        SignUpDto dto = authService.createUser(signUpDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }

    @PostMapping("/{sign-in}")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        authService.loginUser(loginDto);
        return new ResponseEntity<>("Sign in Successful",HttpStatus.OK);

    }
}
