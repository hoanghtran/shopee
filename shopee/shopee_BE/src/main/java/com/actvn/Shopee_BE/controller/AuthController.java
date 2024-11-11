package com.actvn.Shopee_BE.controller;

import com.actvn.Shopee_BE.dto.request.LoginRequest;
import com.actvn.Shopee_BE.dto.request.SignUpRequest;
import com.actvn.Shopee_BE.dto.response.UserInfoResponse;
import com.actvn.Shopee_BE.security.jwt.JwtUtils;
import com.actvn.Shopee_BE.service.UserService;
import com.actvn.Shopee_BE.service.impl.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND)
                    .body(map);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        ResponseCookie cookie = jwtUtils.generateJwtCookie(userDetails);

        UserInfoResponse response = new UserInfoResponse(
                userDetails.getId(),
                cookie.toString(),
                userDetails.getUsername(),
                roles);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(response);
    }

    @PostMapping("/signup")
    public void registerUser(@RequestBody SignUpRequest signUpRequest){
        if(userService.existsByUsername(signUpRequest.getUsername())){
            //is already taken
        }

        // check: email is already in use?

        //1. Create new user account

        //2. Set roles

        //3. save to repo

        //4. return responseEntity
    }
}
