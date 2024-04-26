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

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Query("SELECT u FROM Course u WHERE u.id_c = ?1")
	Optional<Course> findByID(int id_c);
	
	@Query("SELECT u FROM Course u ")
	List<Course> getAllCourses();
	
	
	@Transactional
    @Modifying
    @Query("delete from Course c where c.id_c = :id")
    void deleteCourseById(@Param("id") int id);
	
	
}
