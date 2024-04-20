package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<User> findByEmail(String email);
	
	
	@Query("SELECT u FROM User u WHERE u.disponibilite='True' AND u.specialite = ?1")
	List<User> findBySpec(String spec);

	boolean existsByEmail(String email);
}