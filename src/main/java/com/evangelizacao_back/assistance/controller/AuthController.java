//package com.evangelizacao_back.assistance.controller;
//
//import com.evangelizacao_back.assistance.dto.JwtAuthenticationResponse;
//import com.evangelizacao_back.assistance.dto.LoginRequest;
//import com.evangelizacao_back.assistance.jwt.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.username(),
//                        loginRequest.password()
//                )
//        );
//
//        String jwt = jwtTokenProvider.generateToken(loginRequest.username());
//        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
//    }
//}
