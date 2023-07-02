package tn.conge.domain.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.entitites.Conge;
import tn.conge.domain.entitites.Employee;
import tn.conge.domain.repositories.CongeRepository;
import tn.conge.domain.services.CongeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conges")
@RequiredArgsConstructor
public class CongeController {


    private final CongeRepository congeRepository;

    private final CongeService congeService;

    @GetMapping("/employee/{employeeId}")
    public List<Conge> getCongesByEmployee(@PathVariable Long employeeId) {
        Employee employee = new Employee();
        employee.setId(employeeId);
        return congeService.getCongesByEmployee(employee);
    }


    @GetMapping("/")
    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }

    @PostMapping("/")
    public Conge createConge(@RequestBody Conge conge) {
        return congeRepository.save(conge);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Conge> getCongeById(@PathVariable(value = "id") Long id) {
        Optional<Conge> conge = congeRepository.findById(id);
        if (conge.isPresent()) {
            return ResponseEntity.ok().body(conge.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conge> updateConge(@PathVariable(value = "id") Long id, @RequestBody Conge congeDetails) {
        Optional<Conge> optionalConge = congeRepository.findById(id);
        if (optionalConge.isPresent()) {
            Conge conge = optionalConge.get();
            conge.setEmploye(congeDetails.getEmploye());
            conge.setDateDebut(congeDetails.getDateDebut());
            conge.setDateFin(congeDetails.getDateFin());
            conge.setDecision(congeDetails.getDecision());

            Conge updatedConge = congeRepository.save(conge);
            return ResponseEntity.ok().body(updatedConge);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConge(@PathVariable(value = "id") Long id) {
        Optional<Conge> optionalConge = congeRepository.findById(id);
        if (optionalConge.isPresent()) {
            Conge conge = optionalConge.get();
            congeRepository.delete(conge);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
