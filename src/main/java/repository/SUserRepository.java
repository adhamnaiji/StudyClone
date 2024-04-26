package repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Model.Course;
import Model.SUser;

@Repository
public interface SUserRepository extends JpaRepository<SUser, Integer>{
	//find user by email
	@Query("SELECT u FROM SUser u WHERE u.email = ?1")
	Optional<SUser> findByEmail(String email);
	//find user by id
	@Query("SELECT u FROM SUser u WHERE u.id = ?1")
	SUser findByID(int id);

	@Query("SELECT u FROM SUser u")
	List<SUser> findAllUsers();
	
	
	@Transactional
    @Modifying
    @Query("delete from SUser c where c.id = :id")
    void deleteUserById(@Param("id") int id);
	/*
	@Query("SELECT u FROM Course u where u.mail_of_owner=:mail")
	List<Course> findUserOwnCourses(String mail);
	*/
	
	boolean existsByEmail(String email);

}
