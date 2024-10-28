package com.ms.livro.repository;

import com.ms.livro.entity.livroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface livroRepository extends JpaRepository<livroEntity, Integer> {
}
