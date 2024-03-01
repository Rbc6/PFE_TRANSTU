package com.pfe.Controller;

import com.pfe.Repositories.UserRepository;
import com.pfe.dto.AuthentificationRequest;
import com.pfe.dto.AuthentificationResponse;
import com.pfe.entites.User;
import com.pfe.services.auth.AuthService;
import com.pfe.services.user.UserService;
import com.pfe.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;



    @PostMapping("/login")
    public AuthentificationResponse login(@RequestBody AuthentificationRequest authentificationRequest) throws BadCredentialsException{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getEmail(), authentificationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Incorrect Email Or Password.");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authentificationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthentificationResponse authenticationResponse = new AuthentificationResponse();

        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setRoleId(optionalUser.get().getRole().getId());
        }
        return  authenticationResponse;
    }

    @GetMapping("/users")
    public List<AuthentificationResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<AuthentificationResponse> responseList = new ArrayList<>();
        for (User user : users) {
            AuthentificationResponse response = new AuthentificationResponse();
            response.setUserId(user.getId());
            response.setRoleId(user.getRole().getId());
            responseList.add(response);
        }
        return responseList;
    }


}
