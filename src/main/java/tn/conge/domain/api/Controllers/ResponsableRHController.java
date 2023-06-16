package tn.conge.domain.api.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.Entities.ERole;
import tn.conge.domain.Entities.ResponsableRH;
import tn.conge.domain.Entities.Role;
import tn.conge.domain.Repositories.ReponsableRHRepository;
import tn.conge.domain.Repositories.RoleRepository;
import tn.conge.domain.Repositories.UserRepository;
import tn.conge.domain.payload.request.SignupRequest;
import tn.conge.domain.payload.response.MessageResponse;
import tn.conge.domain.services.impl.ResponsableRHServiceImpl;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/responsablerh")
public class ResponsableRHController {
    @Autowired
    private ResponsableRHServiceImpl responsableRHServiceImp;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReponsableRHRepository responsableRHRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/all")
    public List<ResponsableRH> getAllresponsablerh() {
        return responsableRHServiceImp.getAllResponsableRH();
    }
    @PostMapping("/save")
    public ResponsableRH saveresponsablerh(@RequestBody ResponsableRH responsableRH) {
        return responsableRHServiceImp.createResponsableRH(responsableRH);
    }

    @GetMapping  ("/getone/{id}")
    public ResponsableRH getoneresponsablerh(@PathVariable Long id) {
        return responsableRHServiceImp.getResponsableRHById(id);
    }

    @PutMapping("/update/{id}")
    public ResponsableRH update(@RequestBody ResponsableRH responsableRH, @PathVariable Long id) {

        ResponsableRH rh = responsableRHServiceImp.getResponsableRHById(id);
        if (rh != null) {
            responsableRH.setId(id);
            return responsableRHServiceImp.updateResponsableRH(responsableRH);
        }
        else{
            throw new RuntimeException("FAIL!");
        }

    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String,String> deleteresponsablerh(@PathVariable Long id) {
        HashMap message= new HashMap();
        try{
            responsableRHServiceImp.deleteResponsableRH(id);
            message.put("etat","Responsable RH  deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","Responsable RH not deleted");
            return message;
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerRH(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        ResponsableRH rh = new ResponsableRH(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getMatricule(),signUpRequest.getService());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();



        Role empRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(empRole);




        rh.setRoles(roles);
        responsableRHRepository.save(rh);

        return ResponseEntity.ok(new MessageResponse("ResponsableRH registered successfully!"));
    }
}
