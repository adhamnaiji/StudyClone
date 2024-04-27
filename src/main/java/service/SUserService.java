package service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Course;
import Model.SUser;
import Model.SUser.EmailAlreadyExistsException;
import Model.SUser.InvalidPasswordException;
import Model.SUser.UserNotFoundException;
import repository.CourseRepository;
import repository.SUserRepository;

@Service
public class SUserService {
	
	@Autowired
    private SUserRepository userrepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public Optional<SUser> findByEmail(String email) {
        return userrepository.findByEmail(email);
    }
	
	public SUser findByID(int id) {
        return userrepository.findByID(id);
    }
	
	
	public List<SUser> findAllUsers() {
        return userrepository.findAllUsers();
    }
	
	 public SUser AddUser(SUser user) {
		  if (userrepository.existsByEmail(user.getEmail())) {
		        throw new EmailAlreadyExistsException("Email address already in use. Please choose a different email.");
		    }
			return userrepository.save(user);
		}

	 
	 
	 public Optional<SUser> loginUser(String email, String mdp) throws UserNotFoundException, InvalidPasswordException {

		    Optional<SUser> userOptional = userrepository.findByEmail(email);

		    if (userOptional.isEmpty()) {
		        throw new UserNotFoundException("Email not found. Please check your email address and try again.");
		    }
		    
		    SUser user = userOptional.get();
		    if (!mdp.equals(user.getMdp())) {
		    	System.out.println("pasword mdp : "+mdp);
		    	System.out.println("pasword user : "+user.getMdp());
		        throw new InvalidPasswordException("Incorrect password. Please try again.");
		    }

		    return userOptional;
		}

	 
	
    public void addCourseToUser(String mail, int courseId) {
        SUser user = userrepository.findByEmail(mail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        user.getCourses().add(course);
    
        System.out.println("the user heyy :"+user.getCourses().toArray());
        userrepository.save(user);
    }
    
    public Set<Course> getCoursesForUser(String email) {
        SUser user = userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCourses();
    }
    
    public void DeleteuserById(int id) {
    	userrepository.deleteUserById(id);
    }
    
    
    public List<Course> getCoursesCreatedByUser(SUser user) {
        return user.getOwncourses();
    }
    /*
    public List<Course> getUserOwnCourses(String mail){
    	return userrepository.findUserOwnCourses(mail);
    	
    }*/
}
