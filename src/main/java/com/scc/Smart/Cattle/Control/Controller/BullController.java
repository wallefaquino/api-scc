package com.scc.Smart.Cattle.Control.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scc.Smart.Cattle.Control.Model.Bull;
import com.scc.Smart.Cattle.Control.Model.DTO.WeightDTO;
import com.scc.Smart.Cattle.Control.Service.BullService;

@RestController
@RequestMapping("/scc/v1")
public class BullController {

	@Autowired
	private BullService service;

	@GetMapping(value = "/lots/{id}/bulls")
	public ResponseEntity<List<Bull>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/lots/{id}/bulls/{idBull}")
	public ResponseEntity<Bull> findById(@PathVariable Long idBull) {
		return ResponseEntity.ok(service.findById(idBull));
	}

	@PutMapping(value = "/lots/{id}/bulls/{idBull}")
	public ResponseEntity<Bull> update(@PathVariable Long idBull, @RequestBody Bull bull) {
		return ResponseEntity.ok(service.update(idBull, bull));
	}

	@DeleteMapping(value = "/lots/{id}/bulls/{idBull}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long idBull) {
		service.delete(idBull);
	}

	@PutMapping(value = "/lots/{id}/bulls/{id}/weight")
	public ResponseEntity<Optional<Bull>> updateCurrentWeight(@PathVariable Long idBull, @RequestBody WeightDTO weightDTO) {
		return ResponseEntity.ok(service.updateCurrentWeight(idBull, weightDTO));
	}

	@GetMapping(value = "/lots/{idLot}/bull")
	public ResponseEntity<List<Bull>> findByWeight(@PathVariable Long idLot, @RequestParam double minWeight, @RequestParam double maxWeight) {
		return ResponseEntity.ok(service.findByWeight(idLot, minWeight, maxWeight));
	}
	
	@GetMapping(value = "/bulls")
	public ResponseEntity<List<Bull>> findByBreed(@RequestParam("breed") String breed) {
		return ResponseEntity.ok(service.findByBreed(breed));
	}

}
