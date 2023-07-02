package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.entitites.Replacement;

public interface ReplacementRepository extends JpaRepository<Replacement, Long> {
}