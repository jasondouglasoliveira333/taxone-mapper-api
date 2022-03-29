package br.com.lkm.taxone.mapper.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lkm.taxone.mapper.dto.PageResponse;
import br.com.lkm.taxone.mapper.dto.ScheduleLogDTO;
import br.com.lkm.taxone.mapper.dto.ScheduleLogIntergrationErrorDTO;
import br.com.lkm.taxone.mapper.dto.ScheduleLogStatisticDTO;
import br.com.lkm.taxone.mapper.enums.ScheduleLogStatus;
import br.com.lkm.taxone.mapper.service.ScheduleLogService;

@CrossOrigin
@RestController
@RequestMapping("/schedulelogs")
public class ScheduleLogController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ScheduleLogService scheduleLogService; 

	@GetMapping
	public ResponseEntity<?> list(@RequestParam(name="status") ScheduleLogStatus status,  @RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="size", defaultValue = "10") Integer size){
		try {
			System.out.println("In ScheduleLogController.list");
			PageResponse<ScheduleLogDTO> sPage = scheduleLogService.findAll(status, PageRequest.of(page, size, Direction.DESC, "executionDate"));
			return ResponseEntity.ok(sPage);
		}catch(Exception e) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("statistics")
	public ResponseEntity<?> generateStatitics(){
		try {
			List<ScheduleLogStatisticDTO> slsList = scheduleLogService.groupByStatus();
			return ResponseEntity.ok(slsList);
		}catch(Exception e) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable("id") Integer id){
		try {
			ScheduleLogDTO slDTO = scheduleLogService.get(id);
			return ResponseEntity.ok(slDTO);
		}catch(Exception e) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("{id}/taxOneErrors")
	public ResponseEntity<?> getTaxOneErrors(@PathVariable("id") Integer id, @RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="size", defaultValue = "10") Integer size){
		try {
			PageResponse<ScheduleLogIntergrationErrorDTO> taxOneErrors = scheduleLogService.getTaxtOneErrors(id, PageRequest.of(page, size));
			return ResponseEntity.ok(taxOneErrors);
		}catch(Exception e) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}


}
