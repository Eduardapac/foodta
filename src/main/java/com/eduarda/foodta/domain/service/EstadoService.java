package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.model.Estado;
import com.eduarda.foodta.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        return estadoRepository.salvar(estado);
    }
}
