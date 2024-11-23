package com.ms.usuario.repository;

import com.ms.usuario.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface userRepository extends JpaRepository<userEntity, Integer> {
    UserDetails findByLogin(String login);
}
