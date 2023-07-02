package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.entitites.ReplacementRequest;

public interface ReplacementRequestRepository extends JpaRepository<ReplacementRequest, Long> {
}