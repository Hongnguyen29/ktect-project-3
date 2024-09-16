package com.example.shopping.user;

import com.example.shopping.jwt.JwtTokenUtils;
import com.example.shopping.jwt.dto.JwtRequestDto;
import com.example.shopping.jwt.dto.JwtResponseDto;
import com.example.shopping.user.repo.UserRepository;
import com.example.shopping.user.dto.RegisterUserDto;
import com.example.shopping.user.dto.UserDto;
import com.example.shopping.user.dto.UserProfileDto;
import com.example.shopping.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService  {
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtils jwtTokenUtils;
    private UserRepository userRepository;
    private final CustomUserDetailsService customService;

    public UserService(
            PasswordEncoder passwordEncoder,
            JwtTokenUtils jwtTokenUtils,
            UserRepository userRepository,
            CustomUserDetailsService customService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
        this.customService = customService;
        this.userRepository = userRepository;

        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setEmail("admin@example.com");
            admin.setPhoneNumber("123-456-7890");
            admin.setRole("ROLE_ADMIN,READ.REQUEST,INSPECT.REQUEST");
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user1").isEmpty()) {
            UserEntity user1 = new UserEntity();
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("123456"));
            user1.setName("noob");
            user1.setEmail("admin@example.com");
            user1.setPhoneNumber("123-456-7890");
            user1.setRole("ROLE_USER,OPEN.REQUEST,ORDER,READ.REQUEST");
            userRepository.save(user1);
        }
        if (userRepository.findByUsername("user2").isEmpty()) {
            UserEntity user2 = new UserEntity();
            user2.setUsername("user2");
            user2.setPassword(passwordEncoder.encode("123456"));
            user2.setRole("ROLE_INACTIVE,READ");

            userRepository.save(user2);
        }
    }


     public void registerUser (
             RegisterUserDto dto){
        if (customService.userExists(dto.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists");
         if (!dto.getPassword().equals(dto.getPasswordCheck()))
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Passwords do not match");
        UserEntity newUser = new UserEntity();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setRole("ROLE_INACTIVE,READ");
        userRepository.save(newUser);
    }

    public JwtResponseDto loginUser(
            JwtRequestDto requestDto
    ){
        if (requestDto.getUsername() == null || requestDto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please check your username and password again.");
        }
        UserDetails userDetails;
        try{
            userDetails = customService.
                    loadUserByUsername(requestDto.getUsername());
        }catch (UsernameNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found.");
        }
        if(!passwordEncoder.matches(
                requestDto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password.");

        String jwt = jwtTokenUtils.generateToken(userDetails);
        JwtResponseDto responseDto = new JwtResponseDto();
        responseDto.setToken(jwt);
        return responseDto;
    }
    public UserDto profile(
            String username
    ){
        UserEntity user = userRepository.findByUsername(username).orElseThrow();

        return UserDto.fromEntity(user);
    }

    public UserDto updateProfile(
            String username,
            UserProfileDto profileDto
    ){
        Optional<UserEntity> optionalUser =
                userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            System.out.println("username not exists");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserEntity user = optionalUser.get();
        /*UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));*/
        if(profileDto.getName() != null){
            user.setName(profileDto.getName());
        }
        if(profileDto.getAge() != null){
            user.setAge(profileDto.getAge());
        }
        if(profileDto.getEmail() != null){
            user.setEmail(profileDto.getEmail());
        }
        if(profileDto.getPhoneNumber() != null){
            user.setPhoneNumber(profileDto.getPhoneNumber());
        }
        userRepository.save(user);
        if(user.getName() != null && user.getAge() != null
                && user.getEmail() != null && user.getPhoneNumber() != null
                && Objects.equals(user.getRole(), "ROLE_INACTIVE,READ"
        )){
            user.setRole("ROLE_USER,READ,ORDER");
        }
        userRepository.save(user);

        return UserDto.fromEntity(user);
    }
    public UserDto updateImage(
            String username,
            MultipartFile image
    ){
        Optional<UserEntity> optionalUser =
                userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            System.out.println("username not exists");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        String profileDir = "media/" + username + "/";
        try {
            Files.createDirectories(Path.of(profileDir));
        }catch (IOException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String originalFilename = image.getOriginalFilename();
        String[] filenameSplit = originalFilename.split("\\.");
        String extension = filenameSplit[filenameSplit.length - 1];

        String uploadPath = profileDir + "profile." + extension;
        try {
            image.transferTo(Path.of(uploadPath));
        }catch (IOException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String reqPath = "/static/"+ username + "/profile." + extension;
        UserEntity target = optionalUser.get();

        target.setProfileImage(reqPath);

        return UserDto.fromEntity(userRepository.save(target));


    }




}
