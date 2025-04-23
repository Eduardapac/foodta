package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
import com.eduarda.foodta.domain.model.Cozinha;
import com.eduarda.foodta.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.salvar(cozinha);
    }

    public void excluir(Long id){
        try{
            cozinhaRepository.remover(id);
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoExcepition(String.format("Cozinha ou código %d não pode ser removida, pois está em uso.", id));
        }
        catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaExcepition(String.format("Não existe cadastro de cozinha %d", id));
        }
    }
}
