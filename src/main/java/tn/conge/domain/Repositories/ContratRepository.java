package tn.conge.domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.Entities.Contrat;


public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
