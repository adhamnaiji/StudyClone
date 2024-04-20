package service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Model.User;
import Model.User.EmailAlreadyExistsException;
import Model.User.InvalidPasswordException;
import Model.User.UserNotFoundException;
import repository.UserRepository;

@Service

public class UserService {
	
	
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
	
	/*@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String recipientEmail, String prénom ,String nom,String mdp ) {
	  SimpleMailMessage message = new SimpleMailMessage();
	  message.setFrom("extraaccforff1@gmail.com");
	  message.setTo(recipientEmail);
	  message.setSubject("Ré-initialisation du Mot De Passe  ");
	  message.setText("chèr " + prénom+" " + nom + ", Nous avons recu une demande de réinitialisation du mot de passe \n" +
              "Pour garantir la sécurité de vos données , nous enoyons ce mail. \n\n" +
              " votre mot de passe temporaire est: "+ mdp + " \n vous pouvez le changer après d'authentifier.\n\n" +
              "bonne journée, L'Equipe de TaskNow");
	  mailSender.send(message);
	}
	*/
	
	

	@Autowired
    private UserRepository repository;

	public User signup(User user) throws EmailAlreadyExistsException {
	    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	    String hashedPassword = bcrypt.encode(user.getMdp());
	    user.setMdp(hashedPassword);
	    user.setType("patient");

	    if (repository.existsByEmail(user.getEmail())) {
	        throw new EmailAlreadyExistsException("Email address already in use. Please choose a different email.");
	    }

	    return repository.save(user);
	}
    
    
    
    
    public User saveUser(User user) {
    	BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        String hashe=bcrypt . encode(user.getMdp());
        user.setMdp(hashe);
        user.setType("Employe");
        user.setDisponibilite("True");
		return repository.save(user);
	}
	
    
    
    
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	
	public List<User> findBySpec(String spec){
		return repository.findBySpec(spec);}

	
	
	
	
	public void deleteUser(int id) {
		repository.deleteById(id);
		//return "utilisateur supprimé avec succés !"+id ;
	}
	
	


	public User getUserById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	
	
	
	 public Optional<User> findByEmail(String email) {
	        return repository.findByEmail(email);
	    }
	 
	 
	 
	 

	 public Optional<User> loginEmployee(String email, String mdp) throws UserNotFoundException, InvalidPasswordException {

		    Optional<User> userOptional = repository.findByEmail(email);

		    if (userOptional.isEmpty()) {
		        throw new UserNotFoundException("Email not found. Please check your email address and try again.");
		    }
		    
		    User user = userOptional.get();
		    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		    if (!bcrypt.matches(mdp, user.getMdp())) {
		        throw new InvalidPasswordException("Incorrect password. Please try again.");
		    }

		    return userOptional;
		}


		        
		 
	
	public User updateUser(int id, User newEmploye) {
	    // Assuming "repository" is an instance of some JpaRepository or similar interface
	    java.util.Optional<User> optionalOldEmploye = repository.findById(id);

	    if (optionalOldEmploye.isPresent()) {
	    	User oldEmploye = optionalOldEmploye.get();
	    	
	    	if (newEmploye.getPrenom() != null) {oldEmploye.setPrenom(newEmploye.getPrenom());}
	    	
	    	if (newEmploye.getNom() != null) {oldEmploye.setNom(newEmploye.getNom());}
	    	
	    	if (newEmploye.getTelephone() != null) {oldEmploye.setTelephone(newEmploye.getTelephone());}
	    	
	    	if (newEmploye.getEmail() != null) {oldEmploye.setEmail(newEmploye.getEmail());}
	    	
	    	
	    	
	    	if (newEmploye.getLocalisation() != null) {oldEmploye.setLocalisation(newEmploye.getLocalisation());}
	    	
	    	if (newEmploye.getSpecialite() != null) {oldEmploye.setSpecialite(newEmploye.getSpecialite());}
	    	if (newEmploye.getAge() != 0) {oldEmploye.setAge(newEmploye.getAge());}
	    
	        
	        

	        
	  
	        
	       
	        // Save the updated employee
	        return repository.save(oldEmploye);
	    } else {
	        // Handle the case where the old employee with the given id is not found
	        // You might want to throw an exception or return null, depending on your use case
	        return null;
	    }
	}
	    
	    public User updatePassword(int id, String currentPassword, String newPassword) throws UserNotFoundException, InvalidPasswordException {

	    	  // Find the user by ID
	    	  Optional<User> optionalUser = repository.findById(id);

	    	  if (optionalUser.isEmpty()) {
	    	    throw new UserNotFoundException("User not found. Please check your ID and try again.");
	    	  }

	    	  User user = optionalUser.get();

	    	  // Validate current password
	    	  BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	    	  if (!bcrypt.matches(currentPassword, user.getMdp())) {
	    	    throw new InvalidPasswordException("Incorrect current password. Please try again.");
	    	  }

	    	  // Hash the new password
	    	  String hashedNewPassword = bcrypt.encode(newPassword);
	    	  user.setMdp(hashedNewPassword);

	    	  // Save the updated user
	    	  return repository.save(user);
	    	}
	

	    public User resetPassword(String Email) throws UserNotFoundException {

	        
	        Optional<User> optionalUser = repository.findByEmail(Email);

	        if (optionalUser.isEmpty()) {
	            throw new UserNotFoundException("User not found. Please check your ID and try again.");
	        }

	        User user = optionalUser.get();

		    String x=generateRandomString(12);
	        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	        String hashedNewPassword = bcrypt.encode(x);
	        
	        user.setMdp(hashedNewPassword);
	        //sendEmail(user.getEmail(), user.getPrenom(),user.getNom(),x );

	        return repository.save(user);
	    }



	


}

