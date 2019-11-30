package com.scc.Smart.Cattle.Control.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scc.Smart.Cattle.Control.Model.Bull;
import com.scc.Smart.Cattle.Control.Model.Lot;
import com.scc.Smart.Cattle.Control.Model.DTO.QuotationDTO;
import com.scc.Smart.Cattle.Control.Model.DTO.WeightDTO;
import com.scc.Smart.Cattle.Control.Service.LotService;

@RestController
@RequestMapping("/scc/v1")
public class LotController {

	@Autowired
	private LotService service;

	@GetMapping(value = "/lots")
	public ResponseEntity<List<Lot>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/lots/{id}")
	public ResponseEntity<Lot> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping(value = "/lots")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Lot save(@RequestBody Lot lot) {
		return service.save(lot);
	}

	@PutMapping(value = "/lots/{id}")
	public ResponseEntity<Lot> update(@PathVariable Long id, @RequestBody Lot lot) {
		return ResponseEntity.ok(service.update(id, lot));
	}

	@DeleteMapping(value = "/lots/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		service.delete(id);
	}

	@PostMapping("/lots/{id}/bulls")
	@ResponseStatus(value = HttpStatus.OK)
	public void addBull(@PathVariable Long id, @RequestBody Bull bull) {
		service.addBull(id, bull);
	}
	
	@GetMapping("/lots/{id}/bulls/weight")
	public ResponseEntity<WeightDTO> findTotalWeight(@PathVariable Long id) {
		return ResponseEntity.ok(service.findTotalWeight(id));
	}

	@GetMapping("/lots/")
	public ResponseEntity<List<Lot>> findByStatus(@RequestParam("status") String status) {
		return ResponseEntity.ok(service.findByStatus(status));
	}
	
	@GetMapping("/lots/{id}/quotation")
	public ResponseEntity<QuotationDTO> calculateQuotation(@PathVariable Long id) {
		return ResponseEntity.ok(service.calculateQuotation(id));
	}
}
