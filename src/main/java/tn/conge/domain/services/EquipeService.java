package tn.conge.domain.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.conge.core.security.UserRole;
import tn.conge.domain.entitites.Equipe;
import tn.conge.domain.entitites.User;
import tn.conge.domain.repositories.EquipeRepository;
import tn.conge.domain.repositories.UserRepository;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EquipeService implements  IEquipeService {
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Equipe addEquipe(Equipe equipe) {
        if (equipe != null) {
            equipeRepository.save(equipe);
            return equipe;
        }
        else return null;
    }
    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe getEquipeById(Long id) {
        return equipeRepository.findById(id).orElse(null);
    }

    @Override
    public Equipe updateEquipe(Long id, Equipe updatedEquipe) {
        Equipe existingEquipe = equipeRepository.findById(id).orElse(null);
        if (existingEquipe != null) {
            BeanUtils.copyProperties(updatedEquipe, existingEquipe, "id");
            return equipeRepository.save(existingEquipe);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEquipe(Long id) {
        Equipe equipe = equipeRepository.findById(id).orElse(null);
        if (equipe != null) {
            equipeRepository.delete(equipe);
        }
    }

    public void assignUserToEquipe(Long userId, Long equipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));;
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Equipe not found with ID: " + equipeId));
        user.setEquipe(equipe);
        userRepository.save(user);
    }
    public List<User> getEquipeUsers(Long equipeId) {
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Equipe not found with ID: " + equipeId));

        return equipe.getUsers();
    }
    @Override
    public List<Equipe> getSortedEquipes(String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        return equipeRepository.findAll(sort);
    }

}
