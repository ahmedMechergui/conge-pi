package tn.conge.domain.services;


import tn.conge.domain.Entities.Contrat;

import java.util.List;

public interface ContratService {

    Contrat createContrat(Contrat contrat);
    List<Contrat> getAllContrat();
    Contrat getContratById(Long id);
}
