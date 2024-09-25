package com.auth.backend.Controller;

import com.auth.backend.Config.UserAuthProvider;
import com.auth.backend.Services.UserService;
import com.auth.backend.dto.CredentialsDto;
import com.auth.backend.dto.SignUpDto;
import com.auth.backend.dto.UserDto;
import com.auth.backend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {

        System.out.println("Controlller for login : "+credentialsDto.toString());
        UserDto user = userService.login(credentialsDto);

        user.setToken(userAuthProvider.createToken(user.getLogin()));
        System.out.println(user.toString());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        System.out.println("Entered the register controller ");

        UserDto user = userService.signUp(signUpDto);
        user.setToken(userAuthProvider.createToken(user.getLogin()));

        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
