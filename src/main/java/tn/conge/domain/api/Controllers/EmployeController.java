package tn.conge.domain.api.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/*import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;*/
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.Entities.*;
import tn.conge.domain.Repositories.EmployeRepository;
import tn.conge.domain.Repositories.RoleRepository;
import tn.conge.domain.Repositories.UserRepository;
import tn.conge.domain.payload.request.SignupRequest;
import tn.conge.domain.payload.response.MessageResponse;
import tn.conge.domain.services.impl.ContratServiceImpl;
import tn.conge.domain.services.impl.EmployeServiceImp;
import tn.conge.domain.services.impl.EquipeServiceImpl;

import javax.mail.MessagingException;
/*import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;*/

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employe")
public class EmployeController {
	 @Autowired
	 UserRepository userRepository;
	    @Autowired
		EmployeRepository employeRepository;

	    @Autowired
		RoleRepository roleRepository;

	    @Autowired
		PasswordEncoder encoder;


	    @Autowired
	    private EmployeServiceImp employeServiceImp;

	    @Autowired
	    private EquipeServiceImpl equipeServiceImp;

	    @Autowired
	    private ContratServiceImpl contratServiceImp;

	/**
	 *
	 */
/*	@Autowired
	private JavaMailSender mailSender;*/


	    @GetMapping("/all")
	    public List<Employe> getAllemploye() {
	        return employeServiceImp.getAllEmploye();
	    }
	    @PostMapping("/save")
	    public Employe saveemploye(@RequestBody Employe employe) {
	        return employeServiceImp.createEmploye(employe);
	    }

	    @GetMapping  ("/getone/{id}")
	    public Employe getoneemploye(@PathVariable Long id) {
	        return employeServiceImp.getEmployeById(id);
	    }

	    @GetMapping("/allbyequipe")
	    public List<Employe> getAllemployebyequipe(String nom) {
	        return employeServiceImp.getEmployebyequipe(nom);
	    }
	    @PutMapping("/update/{id}")
	    public Employe update(@RequestBody Employe e, @PathVariable Long id) {

	        Employe e1 = employeServiceImp.getEmployeById(id);
	        if (e1 != null) {
	            e.setId(id);
	            return employeServiceImp.updateEmploye(e);
	        }
	        else{
	            throw new RuntimeException("FAIL!");
	        }

	    }

	    @DeleteMapping("/delete/{id}")
	    public HashMap<String,String> deleteEmploye(@PathVariable Long id) {
	        HashMap message= new HashMap();
	        try{
	            employeServiceImp.deleteEmploye(id);
	            message.put("etat","employe deleted");
	            return message;
	        }
	        catch (Exception e) {
	            message.put("etat","employe not deleted");
	            return message;
	        }
	    }


	    @PostMapping("/signup/{idequipe}/{idcontrat}")
	    public ResponseEntity<?> registerEmploye(@Valid @RequestBody SignupRequest signUpRequest, String email, @PathVariable Long idequipe, @PathVariable Long idcontrat) throws MessagingException {
	        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	        }

	        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	        }

	        // Create new user's account
	        Employe employe = new Employe(signUpRequest.getUsername(), signUpRequest.getEmail(),
	                encoder.encode(signUpRequest.getPassword()),signUpRequest.getNumcin(),signUpRequest.getNom(),signUpRequest.getPrenom(),signUpRequest.getAdresse(),signUpRequest.getTel());

	        Set<String> strRoles = signUpRequest.getRole();
	        Set<Role> roles = new HashSet<>();



	                        Role empRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
	                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	                        roles.add(empRole);


	        employe.setRoles(roles);


//MAIL
			/*SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject("information de votre compte");
			message.setFrom(email);
			message.setTo(signUpRequest.getEmail());
			message.setText("Votre username: " + signUpRequest.getUsername() + " et password: " + signUpRequest.getPassword());
			mailSender.send(message);*/

	        Equipe e= equipeServiceImp.getEquipeById(idequipe);
	        employe.setEquipe(e);

	        Contrat c= contratServiceImp.getContratById(idcontrat);
	        employe.setContrat(c);


	        employeRepository.save(employe);

	        return ResponseEntity.ok(new MessageResponse("Employe registered successfully and mail is send!"));
	    }
	}
