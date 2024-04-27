package controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	//update course
	@PutMapping("course/update/{courseId}")
    public Course updateCourse(@PathVariable int courseId, @RequestBody Course updatedCourse) {
        return service.updateCourse(courseId, updatedCourse);
    }
	
	//add course
		@PostMapping("add/course")
		public Course addCourse(@RequestBody Course course ) {
		    //System.out.println("Received request to add employe: " + user);
		    return service.AddCourse(course);
		}
	
	
	//the users that are enrolled in this course
	 @GetMapping("/courses/{courseId}/users")
	    public Set<SUser> getUsersForCourse(@PathVariable int courseId) {
	        return service.getUsersForCourse(courseId);
	    }
	 
	 //get all the courses
	 @GetMapping("/courses")
	 public  List<Course> getAllCourses(){
		 return service.getAllCourses();
	 }
	 
	 @DeleteMapping("/course/{id}")
	 public  void  DeletCourseByID(@PathVariable int id){
		  service.DeleteCourseById(id);
	 }
	 
	 
	 
	

}
