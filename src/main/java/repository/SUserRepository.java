package repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Model.SUser;

@Repository
public interface SUserRepository extends JpaRepository<SUser, Integer>{
	@Query("SELECT u FROM SUser u WHERE u.email = ?1")
	Optional<SUser> findByEmail(String email);

	@Query("SELECT u FROM SUser u")
	List<SUser> findAllUsers();
	
	
	@Transactional
    @Modifying
    @Query("delete from SUser c where c.id = :id")
    void deleteUserById(@Param("id") int id);
	
	
	
	boolean existsByEmail(String email);

}
