package service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Course;
import Model.SUser;
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
	
	public List<SUser> findAllUsers() {
        return userrepository.findAllUsers();
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
}
