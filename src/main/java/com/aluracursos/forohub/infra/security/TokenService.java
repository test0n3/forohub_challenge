package com.aluracursos.forohub.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aluracursos.forohub.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {

  @Value("${api.security.secret}")
  private String apiSecret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      return JWT.create()
          .withIssuer("forohub")
          .withSubject(user.getLogin())
          .withClaim("id", user.getId())
          .sign(algorithm);
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Error al generar el token", exception);
    }
  }

  public String getSubject(String token) {
    if (token == null) {
      throw new RuntimeException("Token inválido");
    }

    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(apiSecret);
      verifier = JWT.require(algorithm)
          .withIssuer("forohub")
          .build()
          .verify(token);
      verifier.getSubject();
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Token inválido o expirado");
    }

    if (verifier.getSubject() == null) {
      throw new RuntimeException("Verifier inválido");
    }

    return verifier.getSubject();
  }
}
