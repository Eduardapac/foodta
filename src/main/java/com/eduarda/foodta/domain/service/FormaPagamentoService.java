package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.model.FormaPagamento;
import com.eduarda.foodta.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return  formaPagamentoRepository.salvar(formaPagamento);
    }
}
