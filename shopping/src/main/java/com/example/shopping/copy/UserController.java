package com.example.shopping.copy;

import com.example.shopping.AuthenticationFacade;
import com.example.shopping.jwt.dto.JwtRequestDto;
import com.example.shopping.jwt.dto.JwtResponseDto;
import com.example.shopping.user.CustomUserDetailsService;
import com.example.shopping.user.dto.RegisterUserDto;
import com.example.shopping.user.dto.UserDto;
import com.example.shopping.user.dto.UserProfileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

// @RestController
// @Slf4j
// @RequestMapping("/users")
public class UserController {
   /* private final UserService userService;
    private final CustomUserDetailsService detailsService;
    private final AuthenticationFacade authentication;

    public UserController(UserService userService,
                          AuthenticationFacade authentication,
                          CustomUserDetailsService detailsService) {
        this.userService = userService;
        this.authentication = authentication;
        this.detailsService = detailsService;

    }
   @PostMapping("/register")
   public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto dto) {
       try {
           userService.registerUser(dto);
           return ResponseEntity.ok("User registered successfully.");
       } catch (ResponseStatusException e) {
           return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
       }
   }

    @GetMapping("/login")
    public JwtResponseDto loginUser(
            @RequestBody
            JwtRequestDto dto
    ){
        return userService.loginUser(dto);
    }*/
    //---------------------------------------
   /* @PostMapping("/login")
    public JwtResponseDto loginUser(@RequestBody JwtRequestDto requestDto) {
        if (requestDto.getUsername() == null || requestDto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username and password must not be null.");
        }

        UserDetails userDetails;
        try {
            userDetails = detailsService.loadUserByUsername(requestDto.getUsername());
        } catch (UsernameNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found.");
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password.");
        }

        return userService.loginUser(requestDto);
    }*/

/*    @PostMapping("/login")
    public JwtResponseDto loginUser(@RequestBody JwtRequestDto requestDto) {
        try {
            return userService.loginUser(requestDto);
        } catch (ResponseStatusException ex) {
            throw ex; // Forward exception to handle at global or controller level
        }
    }*/
    //-------------------
    /*
    @PutMapping("/{username}/updateProfile")
    public ResponseEntity<String> updateProfile(
            @PathVariable
            String username,
            @RequestBody
            UserProfileDto profileDto
    ){
        String username1 =  authentication.findUsername();
        if (!username1.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to update the profile image for this user.");
        }
        try {
            userService.updateProfile(username1, profileDto);
            return ResponseEntity.ok("User information updated successfully");
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Failed to update user information");
        }
    }
    @PutMapping("/{username}/updateImage")
    public ResponseEntity<?> updateImage(
            @PathVariable
            String username,
            @RequestParam
            MultipartFile image
    ){
        log.info("Request to upload profile img for user: {}", username);
        log.info("File name: {}", image.getOriginalFilename());
        String username1 =  authentication.findUsername();
        if (!username1.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to update the profile image for this user.");
        }
        try {
            UserDto updateUser = userService.updateImage(username,image);
            return ResponseEntity.ok(updateUser);
        }catch (ResponseStatusException e){
            log.error("Error updating profile image: {}", e.getReason(),e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

     */



}
