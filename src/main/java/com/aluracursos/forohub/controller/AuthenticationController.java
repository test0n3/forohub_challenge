package com.aluracursos.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.aluracursos.forohub.domain.user.DataAuthenticationUser;
import com.aluracursos.forohub.domain.user.User;
import com.aluracursos.forohub.infra.security.DataJWTToken;
import com.aluracursos.forohub.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<DataJWTToken> authenticateUser(
      @RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
    System.out.println("dataAuthenticationUser: " + dataAuthenticationUser.toString());
    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
        dataAuthenticationUser.login(),
        dataAuthenticationUser.password());
    System.out.println("authenticationToken: " + authenticationToken.toString());
    var authenticatedUser = authenticationManager.authenticate(authenticationToken);
    System.out.println("authenticatedUser: " + authenticatedUser.toString());
    var JWTToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
    System.out.println("JWTToken: " + JWTToken.toString());
    return ResponseEntity.ok(new DataJWTToken(JWTToken));
  }
}
