package tn.conge.domain.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.conge.domain.Entities.Employe;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    @Query("SELECT e FROM Employe e WHERE e.equipe.nom like :nom")
    List<Employe> GetEmployeByEquipe(@Param("nom") String nom);
}
