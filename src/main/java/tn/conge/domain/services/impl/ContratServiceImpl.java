package tn.conge.domain.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.conge.domain.Entities.Contrat;
import tn.conge.domain.Repositories.ContratRepository;
import tn.conge.domain.services.ContratService;

import java.util.List;

@Service
public class ContratServiceImpl implements ContratService {

    @Autowired
    ContratRepository contratRepository;


    @Override
    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> getAllContrat() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id).get();
    }



}

