package com.eduarda.foodta.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

@NotBlank(message = "O nome do restaurante é obrigatório")
private String nome;

@PositiveOrZero(message = "A taxa de frete deve ser zero ou um valor positivo")
@NotNull(message = "A taxa de frete é obrigatória")
@Column(name="taxa_frete")
private BigDecimal taxaFrete;

@Column(name = "data_atualizacao", columnDefinition = "datetime")
private LocalDateTime dataCadastro;

@UpdateTimestamp
@Column(name = "data_atualizacao", columnDefinition = "datetime")
private LocalDateTime dataAtualizacao;

//@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="cozinha_id")
private Cozinha cozinha;

@Embedded
private Endereco endereco;
}
