package com.ms.usuario.controller;

import com.ms.usuario.entity.loginResponseDTO;
import com.ms.usuario.entity.userEntity;
import com.ms.usuario.entity.autenticaDTO;
import com.ms.usuario.entity.registraDTO;
import com.ms.usuario.security.tokenService;
import com.ms.usuario.repository.userRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;


@Slf4j
@RestController
@RequestMapping("auth")
public class autenticaController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private userRepository repository;
    @Autowired
    private tokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid autenticaDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((userEntity) auth.getPrincipal());

        System.out.println("Login obtido: " + data.login());
        System.out.println("Token recebido: " + token);

        return ResponseEntity.ok(new loginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid registraDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String senhaCripto = new BCryptPasswordEncoder().encode(data.senha());
        userEntity novouser = new userEntity(data.login(), senhaCripto, data.role());
        this.repository.save(novouser);
        return ResponseEntity.ok().build();
    }

}
