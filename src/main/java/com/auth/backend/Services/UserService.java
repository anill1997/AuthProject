package com.auth.backend.Services;

import com.auth.backend.Exceptions.AppException;
import com.auth.backend.Mappers.UserMapper;
import com.auth.backend.dto.CredentialsDto;
import com.auth.backend.dto.SignUpDto;
import com.auth.backend.dto.UserDto;
import com.auth.backend.repository.UserRepository;
import com.auth.backend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto){

        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        System.out.println(credentialsDto.toString());

        System.out.println("Credentials password : "+CharBuffer.wrap(credentialsDto.getPassword()));
        System.out.println("Credentials password : "+credentialsDto.getPassword());
        System.out.println("User  : "+user.toString());

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            System.out.println(userMapper.toUserDto(user));
            return userMapper.toUserDto(user);
        }

        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);

    }

    public UserDto signUp(SignUpDto userDto) {
         Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

         if(optionalUser.isPresent()) {
             throw new AppException("Login already exist", HttpStatus.BAD_REQUEST);
         }

         User user = userMapper.signUpToUser(userDto);

         user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

         User savedUser = userRepository.save(user);

         return userMapper.toUserDto(user);
    }
}
