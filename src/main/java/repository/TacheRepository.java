package repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Model.Tache;
import Model.User;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{
	@Query("SELECT t FROM Tache t WHERE t.patient = ?1")
	List<Tache>findBypatientId(User pat);

	@Query("SELECT t FROM Tache t WHERE t.employe = ?1")
	List<Tache>findByemployeId(User emp);
}





