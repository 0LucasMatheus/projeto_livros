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

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "sinopse")
    private String sinopse;

    @Column(name="publicacao")
    private LocalDate publicacao;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "qtdcapitulos")
    private int qtdcapitulos;

    @Column(name = "id_autor")
    private int id_autor;


}
