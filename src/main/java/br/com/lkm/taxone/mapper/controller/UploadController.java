package br.com.lkm.taxone.mapper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.lkm.taxone.mapper.dto.POCUser;
import br.com.lkm.taxone.mapper.dto.PageResponse;
import br.com.lkm.taxone.mapper.dto.UploadDTO;
import br.com.lkm.taxone.mapper.service.UploadService;

@CrossOrigin
@RestController
@RequestMapping("uploads")
public class UploadController {
	
	private Logger log = LoggerFactory.getLogger(UploadController.class);
	
	private String HI_FOLKS = "Hi FOLKS";
	
	@Autowired 
	private UploadService uploadService;
	
	@GetMapping("ping")
	public String ping() {
		log.info("IN UploadController.ping");
		return HI_FOLKS;
	}
	
	@PostMapping
	public ResponseEntity<?> upload(@RequestParam(name="layoutVersion") String layoutVersion,  
			@RequestParam(name="file") MultipartFile file){
		try {
			log.info("In UploadController.upload:" + file.getOriginalFilename() + " - layoutVersion:" + layoutVersion);
			uploadService.parseFileAndStore(file.getOriginalFilename(), layoutVersion, file.getBytes());
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			log.error("Erro efetuando parser do arquivo", e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> list(@RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="size", defaultValue = "10") Integer size){
		try {
			POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println("user:" + user);
			PageResponse<UploadDTO> uPage = uploadService.findAll(PageRequest.of(page, size, Direction.DESC, "id"));
			return ResponseEntity.ok(uPage);
		}catch(Exception e) {
			log.error("Erro listando os uploads", e);
			return ResponseEntity.badRequest().build();
		}
	}
}
