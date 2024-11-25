package com.ms.livro.repository;

import com.ms.livro.entity.livroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface livroRepository extends JpaRepository<livroEntity, Integer> {


    @Query(value = "SELECT id, isbn, titulo FROM livro", nativeQuery = true)
    List<Object[]> findTituloseIsbns();


}
