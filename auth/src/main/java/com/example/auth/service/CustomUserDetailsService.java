package com.example.auth.service;

import com.example.auth.Repository.UserRepository;
import com.example.auth.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomUserDetailsService(
            @Autowired UserRepository userRepository,
            @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        final UserEntity testUser = new UserEntity();
        testUser.setUsername("testuser");
        testUser.setPassword(passwordEncoder.encode("password"));
        this.userRepository.save(testUser);
    }

    @Override  //
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByName(username);
        if(userEntity == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());
    }
}
