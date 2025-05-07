package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
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
import java.util.Optional;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public List<FormaPagamento> listar(){
        return formaPagamentoRepository.findAll();
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formapagamentoId){
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formapagamentoId);

        if(formaPagamento.isPresent()){
            return ResponseEntity.ok(formaPagamento.get());
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> adicionar(@RequestBody FormaPagamento formaPagamento){
        formaPagamento = formaPagamentoService.salvar(formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamento);
    }

    @PutMapping("/{formaPagamantoId}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento){
        Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);

        if (formaPagamentoAtual.isPresent()){
            BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
            FormaPagamento formaPagamentoSalva = formaPagamentoService.salvar(formaPagamentoAtual.get());
            return ResponseEntity.ok(formaPagamentoSalva);
        }
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> remover(@PathVariable Long formaPagamentoId){
        try{
            formaPagamentoService.excluir(formaPagamentoId);
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeNaoEncontradaExcepition e){
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeEmUsoExcepition e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
