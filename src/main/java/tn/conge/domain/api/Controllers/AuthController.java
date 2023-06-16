package tn.conge.domain.api.Controllers;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;*/
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.conge.core.security.jwt.JwtUtils;
import tn.conge.core.security.services.RefreshTokenService;
import tn.conge.core.security.services.UserDetailsImpl;
import tn.conge.domain.Entities.*;
import tn.conge.domain.Repositories.RoleRepository;
import tn.conge.domain.Repositories.UserRepository;
import tn.conge.domain.exceptions.exceptions.TokenRefreshException;
import tn.conge.domain.payload.request.LoginRequest;
import tn.conge.domain.payload.request.SignupRequest;
import tn.conge.domain.payload.request.TokenRefreshRequest;
import tn.conge.domain.payload.response.JwtResponse;
import tn.conge.domain.payload.response.MessageResponse;
import tn.conge.domain.payload.response.TokenRefreshResponse;


import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;

	  @Autowired
	  RefreshTokenService refreshTokenService;
/*	  @Autowired
	  private JavaMailSender mailSender;*/

	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager
	        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

	    String jwt = jwtUtils.generateJwtToken(userDetails);

	    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

	    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
	        userDetails.getUsername(), userDetails.getEmail(), roles));
	  }

	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
	        encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "employe":
	          Role empRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(empRole);

	          break;
	        case "responsablerh":
	          Role rhRole = roleRepository.findByName(ERole.ROLE_RESPONSABLERH)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(rhRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }



	  @PostMapping("/forgetpassword")
	  public HashMap<String, String> forgotPassword(String email) throws MessagingException {
	    HashMap message = new HashMap();
	    User userExisting = userRepository.findByEmail(email);
	    if (userExisting == null){
	      message.put("USER", "user not found");
	      return message;
	    }
	    UUID token = UUID.randomUUID();
	    userExisting.setPasswordResetToken(token.toString());
	    userExisting.setId(userExisting.getId());



		/*  SimpleMailMessage message1 = new SimpleMailMessage();

		  message1.setSubject("Forgot Password");
		  message1.setFrom("admin@gmail.com");
		  message1.setTo(userExisting.getEmail());
		  message1.setText("<HTML><body> <a href=\"http://localhost:4200/reset/"
	            + userExisting.getPasswordResetToken()+ "\">Forget Password<a/></body></HTML>");
	    mailSender.send(message1);*/

	    userRepository.saveAndFlush(userExisting);
	    message.put("USER", "User found and mail send Successfully");
	    return message;
	  }


	  @PostMapping("/resetpassword/{passwordResetToken}")
	  public HashMap<String, String> resetPassword(@PathVariable String passwordResetToken, String newPassword){
	    HashMap message = new HashMap();
	    User userExisting = userRepository.findByPasswordResetToken(passwordResetToken);

	    if (userExisting != null){
	      userExisting.setId(userExisting.getId());
	      userExisting.setPassword(new BCryptPasswordEncoder().encode(newPassword));
	      userExisting.setPasswordResetToken(null);
	      userRepository.save(userExisting);

	      message.put("Reset Password", "PROCESSED");
	      return message;
	    }else {
	      message.put("Reset Password", "FAILED");
	      return message;
	    }
	  }

	  @PostMapping("/refreshtoken")
	  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
	    String requestRefreshToken = request.getRefreshToken();

	    return refreshTokenService.findByToken(requestRefreshToken)
	        .map(refreshTokenService::verifyExpiration)
	        .map(RefreshToken::getUser)
	        .map(user -> {
	          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
	          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
	        })
	        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
	            "Refresh token is not in database!"));
	  }
	  
	  @PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
	    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Long userId = userDetails.getId();
	    refreshTokenService.deleteByUserId(userId);
	    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
	  }
	  @PostMapping("/change-Password")
	  public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody ChangePasswordRequest request){
	    String username=authentication.getName();
	    Optional<User> user =userRepository.findByUsername(username);
	    if(user==null) {
	      throw new IllegalArgumentException("Invalide user");
	    }
	    User u = user.get();
	    if(!encoder.matches(request.getOldPassword(),u.getPassword())){
	      return new ResponseEntity<>("invalide old password", HttpStatus.EXPECTATION_FAILED);


	    }
	    u.setPassword(encoder.encode(request.getNewPassword()));
	    return ResponseEntity.ok(userRepository.save(u));
	  }
	}
