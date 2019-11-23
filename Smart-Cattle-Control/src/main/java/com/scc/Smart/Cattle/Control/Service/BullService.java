package com.scc.Smart.Cattle.Control.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scc.Smart.Cattle.Control.Exception.BadRequestException;
import com.scc.Smart.Cattle.Control.Exception.ResourceNotFoundException;
import com.scc.Smart.Cattle.Control.Model.Bull;
import com.scc.Smart.Cattle.Control.Repository.BullRepository;

@Service
public class BullService {

	@Autowired
	private BullRepository repository;

	public List<Bull> findAll() {
		return repository.findAll();
	}
	
	public Bull findById(Long id) {
		Bull bull = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));
		
		return bull;
	}
	
	@Transactional
	public void save(Bull bull) {
		try {
			repository.save(bull);
		}catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	@Transactional
	public void delete(Long id) {
		Bull bull = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));
		
		repository.deleteById(bull.getId());
	}
	
	@Transactional
	public Bull update(Long id, Bull currentBull) {
		Bull oldBull = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));
		
		BeanUtils.copyProperties(currentBull, oldBull, "id");
		repository.save(currentBull);
		
		return currentBull;
		
	}
}
 