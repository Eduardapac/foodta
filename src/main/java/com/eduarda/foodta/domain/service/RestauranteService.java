package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
import com.eduarda.foodta.domain.model.Restaurante;
import com.eduarda.foodta.domain.repository.RestauranteRespository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private RestauranteRespository restauranteRespository;

    public Restaurante salvar(Restaurante restaurante){
        return restauranteRespository.salvar(restaurante);
    }

    public void excluir(Long id) {
        try {
            RestauranteRespository.remover(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExcepition(String.format("Estado ou código %d não pode ser removida, pois está em uso.", id));
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExcepition(String.format("Não existe cadastro de estado %d", id));
        }
    }
}
