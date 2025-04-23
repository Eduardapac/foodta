package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
import com.eduarda.foodta.domain.model.Cozinha;
import com.eduarda.foodta.domain.model.Restaurante;
import com.eduarda.foodta.domain.repository.RestauranteRespository;
import com.eduarda.foodta.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRespository restauranteRespository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRespository.listar();
    }

    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Restaurante restaurante = restauranteRespository.buscar(restauranteId);

        if (restaurante != null){
            return  ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        return restauranteService.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Restaurante restauranteAtual = restauranteRespository.buscar(restauranteId);

        if ( restauranteAtual != null){
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            restauranteAtual = restauranteService.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long restauranteId){
        try{
            restauranteService.excluir(restauranteId);
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
