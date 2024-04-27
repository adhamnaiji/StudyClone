package service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Course;
import Model.Course.CourseNotFoundException;
import Model.SUser;
import Model.SUser.EmailAlreadyExistsException;
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
	  
	  
	  public List<Course> getCoursesCreatedByUser(SUser user) {
	        return user.getOwncourses();
	    }
	  
	  
	  public Course AddCourse(Course course) {
		 
			return repository.save(course);
		}
	  
	  
	  
	  public Course updateCourse(int courseId, Course updatedCourse) {
	        Optional<Course> optionalCourse = repository.findById(courseId);
	        if (optionalCourse.isPresent()) {
	            Course course = optionalCourse.get();
	            
	            if(updatedCourse.getTitle()!=null) {
	            course.setTitle(updatedCourse.getTitle());}
	            
	            if(updatedCourse.getDescription()!=null) {
	            course.setDescription(updatedCourse.getDescription());}
	            
	            if(updatedCourse.getPrice()!=null) {
	            course.setPrice(updatedCourse.getPrice());}
	            
	            if(updatedCourse.getCourseUrl()!=null) {
	            course.setCourseUrl(updatedCourse.getCourseUrl());}
	            
	           
	            return repository.save(course);
	        } else {
	            throw new CourseNotFoundException("Course not found with id: " + courseId);
	        }
	    }
	
}
