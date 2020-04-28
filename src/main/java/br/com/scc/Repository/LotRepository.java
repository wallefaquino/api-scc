package br.com.scc.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.scc.Model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

	@Query("update Lot l set l.status = :status where l.id = :id ")
	void updateStatus(@Param("id") Long id, @Param("status") String status);

	List<Lot> findByStatus(@Param("status") String status);
}
