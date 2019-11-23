package com.scc.Smart.Cattle.Control.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scc.Smart.Cattle.Control.Model.Bull;

@Repository
public interface BullRepository extends JpaRepository<Bull, Long> {
	
	@Query("update Bull b set b.current_weight = :current_weight where b.id = :id ")
	void updateCurrentWeight(@Param("id") Long id, @Param("current_weight") double current_weight);
}
