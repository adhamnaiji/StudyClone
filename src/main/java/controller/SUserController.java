package controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import Model.SUser.InvalidPasswordException;
import Model.SUser.UserNotFoundException;
import repository.CourseRepository;
import service.CourseService;
import service.SUserService;

@RestController
@CrossOrigin("*")
public class SUserController {
	
	@Autowired
	private SUserService service;
	
	@Autowired
	private CourseService courseservice;
	
	
	@GetMapping("user/mail/{mail}")
	public Optional<SUser> find(@PathVariable(name = "mail") String email) { 
		return service.findByEmail(email);
	}
	
	@GetMapping("user/{id}")
	public SUser find(@PathVariable(name = "id") int id) { 
		return service.findByID(id);
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
	
	@GetMapping("user/owncourses/{userId}")
    public List<Course> getCreatedCoursesByUserId(@PathVariable int userId) {
        SUser user = service.findByID(userId);
        return courseservice.getCoursesCreatedByUser(user);
    }
	
	//add user 
	@PostMapping("user/add")
	public SUser addUser(@RequestBody SUser user ) {
	    System.out.println("Received request to add employe: " + user);
	    return service.AddUser(user);
	}
	
	//login
	@PostMapping("user/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> requestBody) {
	    String email = requestBody.get("email");
	    String mdp = requestBody.get("mdp");

	    try {
	        Optional<SUser> user = service.loginUser(email, mdp);
	        if (user.isPresent()) {
	            return ResponseEntity.ok(user);  
	        } else {
	            return ResponseEntity.internalServerError().build();
	        }
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (InvalidPasswordException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    }
	    
	}
	
	//update profile user
	 @PutMapping("user/edit/{userId}")
	    public SUser updateUser(@PathVariable int userId, @RequestBody SUser updateduser) {
	        return service.updateUser(userId, updateduser);
	    }
	 
	 //end password
	 @PostMapping("user/reset-password/{id}")
	 public ResponseEntity<String> resetPassword(@PathVariable(name = "id") String Email) {
	   try {
	     service.resetPassword(Email);
	     return ResponseEntity.ok("Password reset email sent successfully!");
	   } catch (UserNotFoundException e) {
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	   } catch (Exception e) { // Catch other unexpected exceptions
	     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password reset failed. Please try again.");
	   }
	 }

	
	
}
