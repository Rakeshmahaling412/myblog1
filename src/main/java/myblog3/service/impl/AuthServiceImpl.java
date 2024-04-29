package myblog3.service.impl;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import myblog3.entity.User;
import myblog3.exception.ResourceNotFound;
import myblog3.payload.LoginDto;
import myblog3.payload.SignUpDto;
import myblog3.repository.AuthRepsotiry;
import myblog3.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthRepsotiry authRepsotiry;
    private ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;


    @Override
    public SignUpDto createUser(SignUpDto signUpDto) {

        if(authRepsotiry.existsByEmail(signUpDto.getEmail())){
            throw new ResourceNotFound("Email Id Already Exist");
        }
        if (authRepsotiry.existsByUsername(signUpDto.getUsername())){
            throw  new ResourceNotFound("User Name Already Exist");
        }
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User save = authRepsotiry.save(user);
        SignUpDto dto = mapToDto(save);


        return dto;
    }

    @Override
    public void loginUser(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(upat);

        SecurityContextHolder.getContext().setAuthentication(authenticate);


    }

    User mapToEntity(SignUpDto signUpDto){
        return modelMapper.map(signUpDto,User.class);

    }
    SignUpDto mapToDto(User user){
        return modelMapper.map(user,SignUpDto.class);

    }
}
