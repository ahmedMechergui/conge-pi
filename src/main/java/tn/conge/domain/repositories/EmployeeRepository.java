package tn.conge.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.conge.domain.entitites.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}