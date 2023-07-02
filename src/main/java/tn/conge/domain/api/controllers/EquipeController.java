package tn.conge.domain.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.entitites.Equipe;
import tn.conge.domain.entitites.User;
import tn.conge.domain.services.IEquipeService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.DocumentException;
import tn.conge.core.pdf.PDFGenerator;



import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    @Autowired
    IEquipeService equipeService;
    @PostMapping("/add")
    @ResponseBody
    public Equipe saveOne(@RequestBody Equipe equipe){
        Equipe newEquipe = equipeService.addEquipe(equipe);
        return newEquipe;
    }


    @GetMapping("/")
    public List<Equipe> getAllEquipes() {
        return equipeService.getAllEquipes();
    }

    @GetMapping("/get/{id}")
    public Equipe getEquipeById(@PathVariable("id") Long id) {
        Equipe equipe = equipeService.getEquipeById(id);
        if (equipe != null) {
            return equipe;
        } else {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Equipe updateEquipe(@PathVariable("id") Long id, @RequestBody Equipe equipe) {
        Equipe updatedEquipe = equipeService.updateEquipe(id, equipe);
        if (updatedEquipe != null) {
            return updatedEquipe;
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEquipe(@PathVariable("id") Long id) {
        equipeService.deleteEquipe(id);
        return new ResponseEntity("Deleted", HttpStatus.FOUND);
    }
    @PostMapping("/{equipeId}/adduser/{userId}")
    public ResponseEntity<String> assignUserToEquipe(@PathVariable("equipeId") Long equipeId,
                                                     @PathVariable("userId") Long userId) {
        equipeService.assignUserToEquipe(userId, equipeId);
        return ResponseEntity.ok("User assigned to Equipe successfully");
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getEquipeUsers(@PathVariable("id") Long id) {
        List<User> users = equipeService.getEquipeUsers(id);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Equipe>> getSortedEquipes(@RequestParam("sortBy") String sortBy) {
        List<Equipe> equipes = equipeService.getSortedEquipes(sortBy);
        return ResponseEntity.ok(equipes);
    }
    @GetMapping("/pdf/{id}")
    public void generatePdf(HttpServletResponse response, @PathVariable("id") Long id ) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_equipeId_" + id + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<User> users = equipeService.getEquipeUsers(id);

        PDFGenerator generator = new PDFGenerator();
        generator.setUsers(users);
        generator.generate(response);
    }
}
