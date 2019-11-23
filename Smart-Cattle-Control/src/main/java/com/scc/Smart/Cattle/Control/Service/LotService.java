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
		return repository.findAll();
	}
	
	public Lot findById(Long id) {
		Lot lot = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));
		
		return lot;
	}
	
	@Transactional
	public void save(Lot newLot) {
		try {
			newLot.setBulls(new ArrayList<Bull>());
			
			repository.save(newLot);
		}catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	@Transactional
	public void delete(Long id) {
		Lot lot = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));
		
		repository.deleteById(lot.getId());
	}
	
	@Transactional
	public Lot update(Long id, Lot currentLot) {
		Lot oldLot = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + id));
		
		BeanUtils.copyProperties(currentLot, oldLot, "id");
		repository.save(currentLot);
		
		return currentLot;
	}	
	
	@Transactional
	public void addBull(Long idLot, Long idBull) {
		Lot lot = repository.findById(idLot).orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + idLot));
		
		lot.getBulls().add(bullService.findById(idBull));
	}
}
