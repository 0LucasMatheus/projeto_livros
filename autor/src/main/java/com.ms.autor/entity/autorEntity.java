package com.ms.autor.entity;


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
@Table(name="autor")
public class autorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", columnDefinition = "TEXT", nullable = false)
    private String nome;

    @Column(name = "bio", columnDefinition = "TEXT", nullable = false)
    private String bio;

    @Column(name="nascimento", nullable = false)
    private LocalDate nascimento;

    @Column(name="morte")
    private LocalDate morte;

    @Column(name = "olid", nullable = false)
    private String olid;
    }