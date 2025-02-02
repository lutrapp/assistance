//package com.evangelizacao_back.assistance.jwt;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    private final Key jwtSecretKey;
//    private final long jwtExpirationMillis = 604800000L; // 7 dias
//
//    public JwtTokenProvider() {
//        // Geração da chave secreta (HMAC com SHA-512)
//        this.jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    }
//
//    /**
//     * Gera um token JWT a partir do nome de usuário.
//     */
//    public String generateToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationMillis);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(jwtSecretKey, SignatureAlgorithm.HS512) // Especifica o algoritmo
//                .compact();
//    }
//
//    /**
//     * Extrai o nome de usuário (subjeto) do token JWT.
//     */
//    public String getUsernameFromToken(String token) {
//        JwtParser jwtParser = Jwts.parser() // Cria o parserBuilder
//                .setSigningKey(jwtSecretKey) // Define a chave para validação
//                .build(); // Construa o JwtParser
//
//        Claims claims = jwtParser.parseClaimsJws(token) // Faz o parse e valida o JWT
//                .getBody(); // Obtém o corpo do token
//
//        return claims.getSubject(); // Retorna o sujeito (normalmente, o username)
//    }
//
//    /**
//     * Valida o token JWT.
//     */
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser()
//                    .setSigningKey(jwtSecretKey)
//                    .build()
//                    .parseClaimsJws(token);
//            return true; // Token válido
//        } catch (JwtException | IllegalArgumentException e) {
//            // Token inválido ou corrompido
//            return false;
//        }
//    }
//}
