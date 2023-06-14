package tn.conge.domain.services;


import tn.conge.domain.Entities.Equipe;

import java.util.List;

public interface EquipeService {

    Equipe createEquipe(Equipe equipe);
    List<Equipe> getAllEquipe();
    Equipe getEquipeById(Long id);
}
