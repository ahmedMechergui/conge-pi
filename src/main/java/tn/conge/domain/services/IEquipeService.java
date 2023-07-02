package tn.conge.domain.services;

import tn.conge.domain.entitites.Equipe;
import tn.conge.domain.entitites.User;

import java.util.List;

public interface IEquipeService {
    List<Equipe> getAllEquipes();
    Equipe addEquipe (Equipe equipe );
    Equipe getEquipeById(Long id);
    Equipe updateEquipe(Long id, Equipe equipe);
    void deleteEquipe(Long id);
    void assignUserToEquipe(Long userId, Long equipeId);
    List<User> getEquipeUsers(Long equipeId);
    List<Equipe> getSortedEquipes(String sortBy);

}
