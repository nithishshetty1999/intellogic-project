package com.user.auth.service;

import com.user.auth.dto.LoginDto;
import com.user.auth.dto.SignUpDto;
import com.user.auth.entity.User;
import com.user.auth.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<String> login(LoginDto loginDto){
        if(loginDto != null && StringUtils.isNotEmpty(loginDto.getUsername()) && StringUtils.isNotEmpty(loginDto.getPassword())) {
            Optional<User> byUsername = userRepository.findByUsername(loginDto.getUsername());
          //  String password = passwordEncoder.encode(loginDto.getPassword());
            if (byUsername != null  && byUsername.isPresent()) {
                    User user = byUsername.get();
                    if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
                        return new ResponseEntity<>("User LogIn successful", HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>("Invalid login credentials", HttpStatus.BAD_REQUEST);
                    }
            } else{
                return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("Invalid login request", HttpStatus.BAD_REQUEST);
        }
    }

    public  ResponseEntity<?> signup(SignUpDto signUpDto){

        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByContact(signUpDto.getContact())){
            return new ResponseEntity<>("Contact is already exists", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setContact(signUpDto.getContact());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
