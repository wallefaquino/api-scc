package com.scc.Smart.Cattle.Control.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scc.Smart.Cattle.Control.Exception.BadRequestException;
import com.scc.Smart.Cattle.Control.Exception.ResourceNotFoundException;
import com.scc.Smart.Cattle.Control.Model.Bull;
import com.scc.Smart.Cattle.Control.Model.DTO.WeightDTO;
import com.scc.Smart.Cattle.Control.Repository.BullRepository;

@Service
public class BullService {

	@Autowired
	private BullRepository repository;

	public List<Bull> findAll() {

		try {
			return repository.findAll();
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	public Bull findById(Long id) {

		Bull bull = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));

		return bull;
	}

	@Transactional
	public void save(Bull bull) {
		try {
			repository.save(bull);
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	@Transactional
	public void delete(Long id) {
		Bull bull = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));

		try {
			repository.deleteById(bull.getId());
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	@Transactional
	public Bull update(Long id, Bull currentBull) {
		Bull oldBull = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));

		try {
			BeanUtils.copyProperties(currentBull, oldBull, "id");
			repository.save(currentBull);

			return currentBull;
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	@Transactional
	public Optional<Bull> updateCurrentWeight(Long id, WeightDTO weight) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bull not found with id: " + id));

		try {
			repository.updateCurrentWeight(id, weight.getWeight());

			return repository.findById(id);
		} catch (RuntimeException e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	public List<Bull> findByBreed(String breed) {
		try {
			return repository.findByBreed(breed);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	public List<Bull> findByWeight(Long id, double minWeight, double maxWeight) {
		try {
			return repository.findByWeights(id, minWeight, maxWeight);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

}
