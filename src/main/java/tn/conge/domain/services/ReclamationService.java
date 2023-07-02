package tn.conge.domain.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.conge.domain.entitites.Equipe;
import tn.conge.domain.entitites.Reclamation;
import tn.conge.domain.repositories.ReclamationRepositroy;

import java.util.List;

@Service
public class ReclamationService implements IReclamationService{
    @Autowired
    ReclamationRepositroy reclamationRepositroy;
    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        if (reclamation != null) {
            reclamationRepositroy.save(reclamation);
            return reclamation;
        }
        else return null;
    }

    @Override
    public List<Reclamation> getAll() {
        return reclamationRepositroy.findAll();
    }

    @Override
    public Reclamation getReclamationById(Long id) {
        return reclamationRepositroy.findById(id).orElse(null);
    }

    @Override
    public Reclamation updateReclamation(Long id, Reclamation updatedReclamation) {
        Reclamation existingReclamation = reclamationRepositroy.findById(id).orElse(null);
        if (existingReclamation != null) {
            BeanUtils.copyProperties(updatedReclamation, existingReclamation, "id");
            return reclamationRepositroy.save(existingReclamation);
        } else {
            return null;
        }
    }

    @Override
    public void deleteReclamation(Long id) {
        Reclamation reclamation = reclamationRepositroy.findById(id).orElse(null);
        if (reclamation != null) {
            reclamationRepositroy.delete(reclamation);
        }
    }
}
