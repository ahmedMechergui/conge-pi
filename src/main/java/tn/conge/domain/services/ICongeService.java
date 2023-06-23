package tn.conge.domain.services;

import org.springframework.data.repository.query.Param;
import tn.conge.domain.entitites.Conge;
import tn.conge.domain.entitites.Employee;

import java.util.List;

public interface ICongeService {

    Conge createConge(Conge conge);

    Conge getCongeById(Long id);

    List<Conge> getAllConges();

    Conge updateConge(Conge conge);

    void deleteCongeById(Long id);

    List<Conge> getCongesByEmployee(Employee employee);


}
