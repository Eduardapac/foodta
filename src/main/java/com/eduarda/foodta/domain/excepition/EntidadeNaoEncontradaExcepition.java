package com.eduarda.foodta.domain.excepition;

public class EntidadeNaoEncontradaExcepition extends RuntimeException{
    public EntidadeNaoEncontradaExcepition(String mensagem){
        super(mensagem);
    }
}
