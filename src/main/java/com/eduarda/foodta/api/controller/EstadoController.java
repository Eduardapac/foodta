package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
import com.eduarda.foodta.domain.model.Estado;
import com.eduarda.foodta.domain.repository.EstadoRepository;
import com.eduarda.foodta.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = estadoRepository.buscar(estadoId);

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }

    public Estado adicionar(@RequestBody Estado estado){
        return  estadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if (estadoAtual != null){
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            estadoAtual = estadoService.salvar(estadoAtual);
            return  ResponseEntity.ok(estadoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId){
        try{
            estadoService.excluir(estadoId);
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