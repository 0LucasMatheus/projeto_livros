package com.ms.usuario.security;

import com.ms.usuario.repository.userRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

@Component
public class securityFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(securityFilter.class);


    @Autowired
    tokenService tokenService;

    @Autowired
    userRepository userRepository;

    /*
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var token = this.recoverToken(request);
            if (token != null) {
                var login = tokenService.validateToken(token);
                UserDetails user = userRepository.findByLogin(login);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }

     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        // Se não encontrar o token, não faça nada
        if (token == null) {
            log.debug("Token não encontrado na requisição.");
            filterChain.doFilter(request, response);
            return;
        }

        log.debug("Token encontrado: {}", token);
        var login = tokenService.validateToken(token);

        if (login == null) {
            log.debug("Token inválido.");
            filterChain.doFilter(request, response);  // Apenas passa adiante
            return;
        }

        log.debug("Login recuperado do token: {}", login);
        UserDetails user = userRepository.findByLogin(login);

        // Se o usuário for encontrado, faz a autenticação
        if (user != null) {
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Usuário autenticado: {}", user.getUsername());
        } else {
            log.debug("Usuário não encontrado.");
        }

        filterChain.doFilter(request, response);  // Continua a execução normal da requisição
    }


    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            log.debug("No Authorization header found");
            return null;
        }
        String token = authHeader.replace("Bearer ", "");
        log.debug("Token found: {}", token);
        return token;
    }


}
