package br.com.lkm.taxone.mapper.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lkm.taxone.mapper.dto.EmailDTO;
import br.com.lkm.taxone.mapper.dto.PageResponse;
import br.com.lkm.taxone.mapper.service.EmailService;


@CrossOrigin
@RestController
@RequestMapping("emails")
public class EmailController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmailService emailService;

	@GetMapping
	public ResponseEntity<?> list(@RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="size", defaultValue = "10") Integer size){
		try {
			PageResponse<EmailDTO> uPage = emailService.findAll(PageRequest.of(page, size, Direction.DESC, "id"));
			return ResponseEntity.ok(uPage);
		}catch(Exception e) {
			log.error("Erro listando os email", e);
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@RequestBody ArrayList<EmailDTO> emails){
		try {
			emails.stream().forEach(email -> {
				emailService.save(email);
			});
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			log.error("Erro salvando o email", e);
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("{emailId}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("emailId") Integer emailId){
		try {
			emailService.delete(emailId);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			log.error("Erro delete o email", e);
			return ResponseEntity.badRequest().build();
		}
	}

}
