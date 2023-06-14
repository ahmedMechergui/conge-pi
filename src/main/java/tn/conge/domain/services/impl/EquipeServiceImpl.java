package tn.conge.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.conge.domain.Entities.Equipe;
import tn.conge.domain.Repositories.EquipeRepository;
import tn.conge.domain.services.EquipeService;

import java.util.List;

@Service
public class EquipeServiceImpl  implements EquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Override
    public Equipe createEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> getAllEquipe() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe getEquipeById(Long id) {
        return equipeRepository.findById(id).get();
    }

}

