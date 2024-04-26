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
import org.springframework.web.bind.annotation.RestController;

import Model.Course;
import Model.SUser;
import service.SUserService;

@RestController
@CrossOrigin("*")
public class SUserController {
	
	@Autowired
	private SUserService service;
	
	@GetMapping("user/mail/{mail}")
	public Optional<SUser> find(@PathVariable(name = "mail") String email) { 
		return service.findByEmail(email);
	}
	
	@GetMapping("/users")
	public List<SUser> findAllUsers() { 
		return service.findAllUsers();
	}
	
	@PostMapping("/user/{mail}/courses/{courseId}")
    public void addUserToCourse(@PathVariable String mail, @PathVariable int courseId) {
        service.addCourseToUser(mail, courseId);
    }
	
	@GetMapping("/user/{mail}/courses")
    public Set<Course> getCoursesForUser(@PathVariable String mail) {
        return service.getCoursesForUser(mail);
    }
	
	@DeleteMapping("/user/{id}")
	 public  void  DeletCourseByID(@PathVariable int id){
		  service.DeleteuserById(id);
	 }
}
