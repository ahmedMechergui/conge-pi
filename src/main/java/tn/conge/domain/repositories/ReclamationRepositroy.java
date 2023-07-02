package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.entitites.Reclamation;

public interface ReclamationRepositroy extends JpaRepository<Reclamation, Long>  {
}
