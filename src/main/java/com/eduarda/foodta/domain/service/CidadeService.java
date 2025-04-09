package com.eduarda.foodta.domain.service;

import com.eduarda.foodta.domain.model.Cidade;
import com.eduarda.foodta.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade){
        return cidadeRepository.salvar(cidade);
    }
}
