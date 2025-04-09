package com.eduarda.foodta.api.controller;

import com.eduarda.foodta.domain.model.Restaurante;
import com.eduarda.foodta.domain.repository.RestauranteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRespository restauranteRespository;

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
}
