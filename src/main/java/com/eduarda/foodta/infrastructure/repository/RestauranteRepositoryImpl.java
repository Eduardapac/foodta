package com.eduarda.foodta.infrastructure.repository;

import com.eduarda.foodta.domain.model.Cozinha;
import com.eduarda.foodta.domain.model.Restaurante;
import com.eduarda.foodta.domain.repository.RestauranteRespository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRespository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar(){
        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }
    @Override
    public Restaurante buscar(Long id){
        return null;
    }
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return null;
    }
    @Override
    public void remover(Long id){
        Restaurante restaurante = buscar(id);

        if (restaurante == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(restaurante);
    }
}
