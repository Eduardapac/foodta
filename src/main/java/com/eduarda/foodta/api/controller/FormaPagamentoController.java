package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.model.FormaPagamento;
import com.eduarda.foodta.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public List<FormaPagamento> listar(){
        return formaPagamentoRepository.listar();
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formapagamentoId){
        FormaPagamento formaPagamento = formaPagamentoRepository.buscar(formapagamentoId);

        if(formaPagamento != null){
            return ResponseEntity.ok(formaPagamento);
        }
        return  ResponseEntity.notFound().build();
    }
}
