package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import Model.User;
import Model.User.EmailAlreadyExistsException;
import Model.User.InvalidPasswordException;
import Model.User.UserNotFoundException;
import service.UserService;

@RestController
@CrossOrigin("*")

public class UserController {
	
	/*@Autowired
	private UserService service;
	
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, String> requestBody) {
	    String email = requestBody.get("email");
	    String mdp = requestBody.get("mdp");

	    try {
	        Optional<User> user = service.loginEmployee(email, mdp);
	        if (user.isPresent()) {
	            return ResponseEntity.ok(user.get());  // Return logged-in user data
	        } else {
	            // Handle unexpected case where login succeeds but user is not present (shouldn't happen)
	            return ResponseEntity.internalServerError().build();
	        }
	    } catch (UserNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (InvalidPasswordException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    }
	}

	
	
	
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody User user) {
	    try {
	        User newUser = service.signup(user);
	        if (newUser != null) {
	            return ResponseEntity.ok().build(); 
	        } else {
	            // Handle unexpected case where service returns null (shouldn't happen)
	            return ResponseEntity.internalServerError().build();
	        }
	    } catch (EmailAlreadyExistsException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    } catch (Exception e) { // Catch other unexpected exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed. Please try again.");
	    }
	}
	
	
	@PostMapping("/add")
	public User addUser(@RequestBody User user ) {
	    System.out.println("Received request to add employe: " + user);
	    return service.saveUser(user);
	}
	

	@GetMapping("/users")
	public List<User> findAllUsers() { 
		return service.getUsers();
	}	

	@PutMapping("/update/{id}")
	public User updateUser (@PathVariable(name = "id") int userId, @RequestBody User user) { 
		return service.updateUser(userId,user);
}


	@DeleteMapping("/delete/{id}")
	public void deleteUser (@PathVariable(name = "id") int userId) { 
		 service.deleteUser(userId);
	}


	@GetMapping("/user/{id}")
	public User findUserById(@PathVariable(name = "id") int userId) { 
		return service.getUserById(userId);
}
	@GetMapping("/mail/{mail}")
	public Optional<User> find(@PathVariable(name = "mail") String email) { 
		return service.findByEmail(email);}



@GetMapping("/spec/{spec}")
public List<User> dispo(@PathVariable(name = "spec") String spec) { 
	return service.findBySpec(spec);}



@PutMapping("/passwordupdate/{id}")
public ResponseEntity<Object> updatePassword(@PathVariable(name = "id") int userId,
                                             @RequestBody Map<String, String> requestBody) {
  String currentPassword = requestBody.get("currentPassword");
  String newPassword = requestBody.get("newPassword");

  try {
    User updatedUser = service.updatePassword(userId, currentPassword, newPassword);
    if (updatedUser != null) {
      return ResponseEntity.ok().body("Password updated successfully!");
      
    } else {
      
      return ResponseEntity.internalServerError().build();
    }
  } catch (UserNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  } catch (InvalidPasswordException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  } catch (Exception e) { // Catch other unexpected exceptions
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password update failed. Please try again.");
  }
}

@PostMapping("/reset-password/{id}")
public ResponseEntity<Map<String, String>> resetPassword(@PathVariable(name = "id") String Email) {
  try {
    service.resetPassword(Email);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Password reset email sent successfully!");
    return ResponseEntity.ok(response);
  } catch (UserNotFoundException e) {
    Map<String, String> response = new HashMap<>();
    response.put("error", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  } catch (Exception e) {
    Map<String, String> response = new HashMap<>();
    response.put("error", "Password reset failed. Please try again.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}

*/

}
