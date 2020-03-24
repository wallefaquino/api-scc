package com.scc.Smart.Cattle.Control.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scc.Smart.Cattle.Control.Model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

	@Query("update Lot l set l.status = :status where l.id = :id ")
	void updateStatus(@Param("id") Long id, @Param("status") String status);

	List<Lot> findByStatus(@Param("status") String status);
}
