package tn.conge.domain.api.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.Entities.Equipe;
import tn.conge.domain.services.impl.EquipeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    EquipeServiceImpl equipeServiceImp;
    @GetMapping("/all")
    public List<Equipe> getAllequipe() {
        return equipeServiceImp.getAllEquipe();
    }
    @PostMapping("/save")
    public Equipe saveequipe(@RequestBody Equipe equipe) {

        return equipeServiceImp.createEquipe(equipe);
    }
}