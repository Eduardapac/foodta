package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.model.Cozinha;
import com.eduarda.foodta.domain.model.FormaPagamento;
import com.eduarda.foodta.domain.repository.FormaPagamentoRepository;
import com.eduarda.foodta.domain.service.FormaPagamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private FormaPagamentoService formaPagamentoService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento){
        return formaPagamentoRepository.salvar(formaPagamento);
    }

    @PutMapping("/{formaPagamantoId}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento){
        FormaPagamento formaPagamentoAtual = formaPagamentoRepository.buscar(formaPagamentoId);

        if (formaPagamentoId != null){
            BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
            formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);
            return ResponseEntity.ok(formaPagamentoAtual);
        }
        return  ResponseEntity.notFound().build();
    }

}
