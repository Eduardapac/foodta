package com.eduarda.foodta.infrastructure.repository;

import com.eduarda.foodta.domain.model.Cidade;
import com.eduarda.foodta.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return null;
    }

    @Override
    public Cidade salvar(Cidade cidade) {
        return null;
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cidade cidade = buscar(id);
        if (cidade == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}
