package com.eduarda.foodta.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_restaurante")
public class Restaurante {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@EqualsAndHashCode.Include
private Long id;

private String nome;

@Column(name="taxa_frete")
private BigDecimal taxaFrete;

//@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="cozinha_id")
private Cozinha cozinha;

@Embedded
private Endereco endereco;
}
