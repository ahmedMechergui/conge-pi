package tn.conge.domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.Entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
