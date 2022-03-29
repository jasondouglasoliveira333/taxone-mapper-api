package br.com.lkm.taxone.mapper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lkm.taxone.mapper.dto.TaxOneApiDTO;
import br.com.lkm.taxone.mapper.service.TaxOneApiService;

@CrossOrigin
@RestController
@RequestMapping("taxoneapis")
public class TaxOneApiController {
	
	private static final Logger log = LoggerFactory.getLogger(TaxOneApiController.class);
	
	@Autowired
	private TaxOneApiService taxOneApiService; 

	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable("id") Integer id){
		try {
			TaxOneApiDTO toDTO = taxOneApiService.getOne(id);
			return ResponseEntity.ok(toDTO);
		}catch (Exception e) {
			log.error("Erro obtendo o taxone api config", e);
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody TaxOneApiDTO toDTO){
		try {
			taxOneApiService.save(toDTO);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			log.error("Erro obtendo o taxone api config", e);
			return ResponseEntity.badRequest().build();
		}
	}

}
