package br.com.scc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.scc.Model.Bull;

@Repository
public interface BullRepository extends JpaRepository<Bull, Long> {
	
	@Query("update Bull b set b.current_weight = :current_weight where b.id = :id ")
	void updateCurrentWeight(@Param("id") Long id, @Param("current_weight") double current_weight);
	
	@Query(value = "SELECT * FROM bull WHERE bull.lot_id = 1 AND bull.current_weight >= 320 AND bull.current_weight <= 330;", nativeQuery = true)
	List<Bull> findByWeights(@Param("id") Long id, @Param("minWeight") double minWeight, @Param("maxWeight") double maxWeight);	
	
	List<Bull> findByBreed(@Param("breed") String breed);
}
