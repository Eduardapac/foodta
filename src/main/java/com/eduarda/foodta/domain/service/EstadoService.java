package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.excepition.EntidadeEmUsoExcepition;
import com.eduarda.foodta.domain.excepition.EntidadeNaoEncontradaExcepition;
import com.eduarda.foodta.domain.model.Estado;
import com.eduarda.foodta.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void excluir(Long id) {
        try {
            estadoRepository.remover(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExcepition(String.format("Estado ou código %d não pode ser removida, pois está em uso.", id));
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExcepition(String.format("Não existe cadastro de estado %d", id));
        }
    }
}