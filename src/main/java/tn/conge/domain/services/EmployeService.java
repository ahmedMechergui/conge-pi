package tn.conge.domain.services;



import tn.conge.domain.Entities.Employe;

import java.util.List;

public interface EmployeService {

    Employe createEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    void deleteEmploye(Long id);
    Employe getEmployeById(Long id);
    List<Employe> getAllEmploye();
    public List<Employe> getEmployebyequipe(String nom);
}
