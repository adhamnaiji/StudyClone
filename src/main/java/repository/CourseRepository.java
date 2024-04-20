package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Query("SELECT u FROM Course u WHERE u.id_c = ?1")
	Optional<Course> findByID(int id_c);
}
