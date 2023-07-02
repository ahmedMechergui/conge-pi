package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.entitites.Equipe;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Long>  {
   // List<Equipe> findByNameContainingIgnoreCase(String nom);
}
