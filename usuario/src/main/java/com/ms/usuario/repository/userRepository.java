package com.ms.usuario.repository;

import com.ms.livro.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<userEntity, Integer> {
}
