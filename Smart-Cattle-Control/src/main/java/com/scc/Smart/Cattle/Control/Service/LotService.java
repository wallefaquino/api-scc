package com.scc.Smart.Cattle.Control.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scc.Smart.Cattle.Control.Exception.BadRequestException;
import com.scc.Smart.Cattle.Control.Exception.ResourceNotFoundException;
import com.scc.Smart.Cattle.Control.Model.Bull;
import com.scc.Smart.Cattle.Control.Model.Lot;
import com.scc.Smart.Cattle.Control.Repository.LotRepository;

@Service
public class LotService {

	@Autowired
	private LotRepository repository;

	@Autowired
	private BullService bullService;

	public List<Lot> findAll() {

		try {
			return repository.findAll();
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	public Lot findById(Long id) {
		Lot lot = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));

		return lot;
	}

	@Transactional
	public Lot save(Lot newLot) {
		try {
			newLot.setBulls(new ArrayList<Bull>());

			repository.save(newLot);
			return newLot;
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	@Transactional
	public void delete(Long id) {
		Lot lot = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));

		try {
			repository.deleteById(lot.getId());
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	@Transactional
	public Lot update(Long id, Lot currentLot) {
		Lot oldLot = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));
		
		try {
			
				BeanUtils.copyProperties(currentLot, oldLot, "id");
				repository.save(currentLot);
				
				return currentLot;	
		}catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	@Transactional
	public void addBull(Long id, Bull bull) {
		Lot lot = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));

		try {

			Bull newBull = new Bull();

			newBull.setBreed(bull.getBreed());
			newBull.setInput_weight(bull.getInput_weight());
			newBull.setCurrent_weight(bull.getCurrent_weight());
			bullService.save(newBull);

			lot.getBulls().add(newBull);

			repository.save(lot);
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}

	}
}
