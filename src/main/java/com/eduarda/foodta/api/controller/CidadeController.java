package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
import com.eduarda.foodta.domain.model.Cidade;
import com.eduarda.foodta.domain.repository.CidadeRepository;
import com.eduarda.foodta.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cidades")
@RestController
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
        Cidade cidade = cidadeRepository.buscar(cidadeId);

        if(cidade != null){
            return  ResponseEntity.ok(cidade);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        return cidadeService.salvar(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

        if (cidadeAtual != null){
            BeanUtils.copyProperties(cidadeAtual, "id");
            cidadeAtual = cidadeService.salvar(cidadeAtual);
            return ResponseEntity.ok(cidadeAtual);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId){
        try{
            cidadeService.excluir(cidadeId);
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
