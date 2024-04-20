package controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Model.Course;
import Model.SUser;
import service.CourseService;


@RestController
@CrossOrigin("*")
public class CourseController {
	@Autowired
	private CourseService service;
	
	
	@GetMapping("/course/{id}")
	public Optional<Course> findCourseById(@PathVariable(name = "id") int Courseid) { 
		return service.findByID(Courseid);
}
	
	
	 @GetMapping("/courses/{courseId}/users")
	    public Set<SUser> getUsersForCourse(@PathVariable int courseId) {
	        return service.getUsersForCourse(courseId);
	    }
	

}
