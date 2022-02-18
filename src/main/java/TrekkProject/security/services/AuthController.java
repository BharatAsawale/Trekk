package TrekkProject.security.services;

import TrekkProject.security.requestResponse.JwtResponse;
import TrekkProject.security.requestResponse.LoginRequest;
import TrekkProject.security.requestResponse.MessageResponse;
import TrekkProject.security.User;
import TrekkProject.security.UserRepository;
import TrekkProject.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getFirstname(),
												 userDetails.getLastname(),
												 userDetails.getEmail()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

		if (userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity
					.badRequest()
		 			.body(new MessageResponse("Error: Email is already in use!"));
		}

		User user1=new User(user.getId(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				encoder.encode(user.getPassword()));

		userRepository.save(user1);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
