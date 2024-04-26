package service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Course;
import Model.SUser;
import repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
    private CourseRepository repository;
	
	public Optional<Course> findByID(int id_c) {
        return repository.findByID(id_c);
    }
	
	
	  public Set<SUser> getUsersForCourse(int courseId) {
	        Course course = repository.findByID(courseId)
	                .orElseThrow(() -> new RuntimeException("Course not found"));

	        return course.getUsers();
	    }
	  
	  public List<Course> getAllCourses(){
		  return repository.getAllCourses();  }
	  
	  public void DeleteCourseById(int id) {
	        repository.deleteCourseById(id);
	    }
	
}
