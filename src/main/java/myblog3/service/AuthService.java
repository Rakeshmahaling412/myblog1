package myblog3.service;

import myblog3.payload.LoginDto;
import myblog3.payload.SignUpDto;

public interface AuthService {
    SignUpDto createUser(SignUpDto signUpDto);

    void loginUser(LoginDto loginDto);
}
