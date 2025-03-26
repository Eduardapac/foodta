package com.eduarda.foodta.infrastructure.repository;

import com.eduarda.foodta.domain.model.Estado;
import com.eduarda.foodta.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EstadoRepoaitoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return null;
    }

    @Override
    public Estado salvar(Estado estado) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
