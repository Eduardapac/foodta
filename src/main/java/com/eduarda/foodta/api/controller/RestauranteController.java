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
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRespository restauranteRespository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRespository.findAll();
    }

    @GetMapping("/{restaurnateId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Optional<Restaurante> restaurante = restauranteRespository.findById(restauranteId);

        if (restaurante.isPresent()){
            return  ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante){
        restaurante = restauranteService.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Optional<Restaurante> restauranteAtual = restauranteRespository.findById(restauranteId);

        if ( restauranteAtual.isPresent()){
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            Restaurante restauranteSalvar = restauranteService.salvar(restauranteAtual.get());
            return ResponseEntity.ok(restauranteSalvar);
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
