package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.exception.EntidadeEmUsoException;
import com.eduarda.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.eduarda.foodta.domain.model.Restaurante;
import com.eduarda.foodta.domain.repository.RestauranteRespository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private RestauranteRespository restauranteRespository;

    public Restaurante salvar(Restaurante restaurante){
        return restauranteRespository.save(restaurante);
    }

    public void excluir(Long id) {
        try {
            restauranteRespository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Restaurante ou código %d não pode ser removida, pois está em uso.", id));
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de restaurante %d", id));
        }
    }
}
