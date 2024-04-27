package service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Model.Course;
import Model.SUser;
import Model.Course.CourseNotFoundException;
import Model.SUser.EmailAlreadyExistsException;
import Model.SUser.InvalidPasswordException;
import Model.SUser.UserNotFoundException;
import repository.CourseRepository;
import repository.SUserRepository;

@Service
public class SUserService {
	
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String recipientEmail, String prénom ,String nom,String mdp ) {
	  SimpleMailMessage message = new SimpleMailMessage();
	  message.setFrom("extraaccforff1@gmail.com");
	  message.setTo(recipientEmail);
	  message.setSubject("Ré-initialisation du Mot De Passe  ");
	  message.setText("chèr " + prénom+" " + nom + ", Nous avons recu une demande de réinitialisation du mot de passe \n" +
              "Pour garantir la sécurité de vos données , nous enoyons ce mail. \n\n" +
              " votre mot de passe temporaire est: "+ mdp + " \n vous pouvez le changer après d'authentifier.\n\n" +
              "Adham Naiji");
	  mailSender.send(message);
	}
	
	
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
    
    
    
    public SUser updateUser(int userId, SUser updateduser) {
        Optional<SUser> optionaluser = userrepository.findById(userId);
        if (optionaluser.isPresent()) {
            SUser user = optionaluser.get();
            if(updateduser.getNom()!=null) {
            	
            	user.setNom(updateduser.getNom());
            }
if(updateduser.getPrenom()!=null) {
            	
    user.setPrenom(updateduser.getPrenom());

            }

if(updateduser.getAdresse()!=null) {
	
    user.setAdresse(updateduser.getAdresse());
}

if(updateduser.getDate_birth()!=null) {
	
    user.setDate_birth(updateduser.getDate_birth());
}


if(updateduser.getPhone_number()!=null) {
	
    user.setPhone_number(updateduser.getPhone_number());
}

if(updateduser.getPersonalWebsite()!=null) {
	
    user.setPersonalWebsite(updateduser.getPersonalWebsite());

}

if(updateduser.getDescriptionProfile()!=null) {
	
    user.setDescriptionProfile(updateduser.getDescriptionProfile());
}

if(updateduser.getFacebbok()!=null) {
	
    user.setFacebbok(updateduser.getFacebbok());
}

if(updateduser.getLinkedin()!=null) {
	
    user.setLinkedin(updateduser.getLinkedin());
}
if(updateduser.getInstagram()!=null) {
	
    user.setInstagram(updateduser.getInstagram());
}
            
            
           
            return userrepository.save(user);
        } else {
            throw new UserNotFoundException("user not found with id: " + userId);
        }
    }
    
    
    
    public static String generateRandomString(int length) {
        int leftLimit = 48; // '0'
        int rightLimit = 122; // 'z'
        Random random = new Random();

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(rightLimit - leftLimit) + leftLimit;
            builder.append((char) randomInt);
        }
        return builder.toString();
    }
	
    public SUser resetPassword(String Email) throws UserNotFoundException {

        
        Optional<SUser> optionalUser = userrepository.findByEmail(Email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found. Please check your ID and try again.");
        }

        SUser user = optionalUser.get();

	  
        sendEmail(user.getEmail(), user.getPrenom(),user.getNom(),user.getMdp() );

        return userrepository.save(user);
    }
    
    
    
}
