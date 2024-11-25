package com.ms.autor.repository;

import com.ms.autor.entity.autorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface autorRepository extends JpaRepository<autorEntity, Integer> {

    autorEntity findByNome(String nome);

    @Query(value = "SELECT nome, id, olid FROM autor", nativeQuery = true)
    List<Object[]> findNomeEId();

}
