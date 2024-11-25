package com.ms.livro.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="livro")
public class livroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "sinopse", nullable = false, columnDefinition = "TEXT")
    private String sinopse;

    @Column(name="publicacao", nullable = false)
    private LocalDate publicacao;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "qtdcapitulos", nullable = false)
    private int qtdcapitulos;

    @Column(name = "id_autor", nullable = false)
    private int id_autor;


}
