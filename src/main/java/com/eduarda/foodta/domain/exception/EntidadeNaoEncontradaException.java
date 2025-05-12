package com.eduarda.foodta.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String massage){
        super(massage);
    }
}
