package repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Model.SUser;

@Repository
public interface SUserRepository extends JpaRepository<SUser, Integer>{
	@Query("SELECT u FROM SUser u WHERE u.email = ?1")
	Optional<SUser> findByEmail(String email);

	boolean existsByEmail(String email);

}
