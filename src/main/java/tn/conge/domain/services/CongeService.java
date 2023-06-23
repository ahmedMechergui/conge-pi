package tn.conge.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.conge.domain.entitites.Conge;
import tn.conge.domain.entitites.Employee;
import tn.conge.domain.repositories.CongeRepository;

import java.util.List;

@Service
public class CongeService  implements  ICongeService{

    @Autowired
    private CongeRepository congeRepository;

    public Conge createConge(Conge conge) {
        return congeRepository.save(conge);
    }

    public Conge getCongeById(Long id) {
        return congeRepository.findById(id).orElse(null);
    }

    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }


    public Conge updateConge(Conge conge) {
        return congeRepository.save(conge);
    }



    @Override
    public List<Conge> getCongesByEmployee(Employee employee) {
        return congeRepository.findByEmployee(employee);
    }


    public void deleteCongeById(Long id) {
        congeRepository.deleteById(id);
    }
}
