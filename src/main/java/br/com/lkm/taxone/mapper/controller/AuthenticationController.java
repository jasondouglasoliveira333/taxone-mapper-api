package br.com.lkm.taxone.mapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lkm.taxone.mapper.dto.AutenticationRequest;
import br.com.lkm.taxone.mapper.dto.AutenticationResponse;
import br.com.lkm.taxone.mapper.service.JwtUserDetailsService;
import br.com.lkm.taxone.mapper.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
//	@Autowired
//	private UserRepository userRepository; 
//
//	@GetMapping
//	public void createUser() {
//		User u = new User();
//		u.setName("jason_2");
//		u.setPassword("$2a$10$VHMKDc4JQtHB9vhTKxGYxuwk.A9vxgkho/ufVR5c5IrmTg5tX24WS");
//		u.setCreationDate(LocalDateTime.now());
//		userRepository.save(u);
//	}
//	
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AutenticationRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AutenticationResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			Object o = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			System.out.println("authenticated:" + o);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}