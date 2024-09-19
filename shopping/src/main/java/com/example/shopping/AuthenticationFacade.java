package com.example.shopping;

import com.example.shopping.user.entity.UserEntity;
import com.example.shopping.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final UserRepository userRepository;

    public String findUsername (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        return  ((UserDetails)authentication.getPrincipal()).getUsername();
    }
    public UserEntity findUser (){
        return userRepository.findByUsername(findUsername()).orElseThrow();
    }
}
