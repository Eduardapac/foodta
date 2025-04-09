package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.model.Restaurante;
import com.eduarda.foodta.domain.repository.RestauranteRespository;

public class RestauranteService {

    private RestauranteRespository restauranteRespository;

    public Restaurante salvar(Restaurante restaurante){
        return restauranteRespository.salvar(restaurante);
    }
}
