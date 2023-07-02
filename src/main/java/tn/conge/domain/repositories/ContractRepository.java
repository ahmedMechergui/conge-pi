package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.conge.domain.entitites.Contract;


public interface ContractRepository extends JpaRepository<Contract, Long> {
}
