package tn.conge.domain.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.entitites.Equipe;
import tn.conge.domain.entitites.Reclamation;
import tn.conge.domain.services.IReclamationService;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/reclamations")
public class ReclamationController {
    @Autowired
    IReclamationService reclamationService;
    @PostMapping("/add")
    @ResponseBody
    public Reclamation saveOne(@RequestBody Reclamation reclamation){
        Reclamation newRec = reclamationService.addReclamation(reclamation);
        return newRec;
    }
    @GetMapping("/")
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = reclamationService.getAll();
        return reclamations;
    }

    @GetMapping("/get/{id}")
    public Reclamation getReclamationById(@PathVariable("id") Long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        if (reclamation != null) {
            return reclamation;
        } else {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Reclamation updateEquipe(@PathVariable("id") Long id, @RequestBody Reclamation reclamation) {
        Reclamation updatedReclamation = reclamationService.updateReclamation(id, reclamation);
        if (updatedReclamation != null) {
            return updatedReclamation;
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReclamation(@PathVariable("id") Long id) {
        reclamationService.deleteReclamation(id);
        return new ResponseEntity("Deleted", HttpStatus.FOUND);
    }
}
