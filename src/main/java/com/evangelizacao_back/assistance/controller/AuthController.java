package com.evangelizacao_back.assistance.controller;

import com.evangelizacao_back.assistance.dto.LoginRequest;
import com.evangelizacao_back.assistance.dto.LoginResponse;
import com.evangelizacao_back.assistance.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        try {
            // Autenticar usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            // Obter UserDetails autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gerar Token
            String token = jwtService.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testJwt() {
        return ResponseEntity.ok("Autorizado com sucesso!");
    }
}
