package com.eduarda.foodta.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Endereco {
    @NotBlank(message = "Este campo é obrigatório")
    @Column(name ="endereco_cep")
    private String cep;

    @NotBlank(message = "Este campo é obrigatório")
    @Column(name ="endereco_logradouro")
    private String logradouro;

    @NotNull(message = "Este campo é obrigatório")
    @Column(name ="endereco_numero")
    private String numero;

    @NotBlank(message = "Este campo é obrigatório")
    @Column(name ="endereco_complemento")
    private String complemento;

    @NotBlank(message = "Este campo é obrigatório")
    @Column(name ="endereco_bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name ="endereco_cidade_id")
    private Cidade cidade;

}
