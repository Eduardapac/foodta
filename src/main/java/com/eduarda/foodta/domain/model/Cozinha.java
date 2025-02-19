package com.eduarda.foodta.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name="tb_cozinha")
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nome_cozinha", length = 50)
    private String nome;
}
