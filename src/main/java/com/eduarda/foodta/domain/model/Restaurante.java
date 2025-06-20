package com.eduarda.foodta.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

@Column(name = "data_atualizacao", columnDefinition = "datetime")
private LocalDateTime dataCadastro;

@UpdateTimestamp
@Column(name = "data-atualizacao", columnDefinition = "datetime")
private LocalDateTime dataAtualização;

//@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="cozinha_id")
private Cozinha cozinha;

@Embedded
private Endereco endereco;
}
