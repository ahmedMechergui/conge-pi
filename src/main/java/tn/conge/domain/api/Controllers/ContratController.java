package tn.conge.domain.api.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.Entities.Contrat;
import tn.conge.domain.services.impl.ContratServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/contrat")
public class ContratController {
	 @Autowired
	    private ContratServiceImpl contratServiceImp;

	    @GetMapping("/all")
	    public List<Contrat> getAllcontrat() {
	        return contratServiceImp.getAllContrat();
	    }
	    @PostMapping("/save")
	    public Contrat saveemploye(@RequestBody Contrat contrat) {

	        return contratServiceImp.createContrat(contrat);
	    }

}
