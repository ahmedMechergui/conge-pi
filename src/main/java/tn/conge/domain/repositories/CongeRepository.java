package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.entitites.Conge;
import tn.conge.domain.entitites.Employee;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Long> {

    List<Conge> findByEmployee(Employee employee);
}
