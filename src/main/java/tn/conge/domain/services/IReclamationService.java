package tn.conge.domain.services;

import tn.conge.domain.entitites.Equipe;
import tn.conge.domain.entitites.Reclamation;

import java.util.List;

public interface IReclamationService {
    Reclamation addReclamation  (Reclamation reclamation );
    List<Reclamation> getAll  ( );
    Reclamation getReclamationById(Long id);
    Reclamation updateReclamation(Long id, Reclamation reclamation);
    void deleteReclamation(Long id);
}
