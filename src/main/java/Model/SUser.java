package Model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="Suser")
public class SUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom ;
	private String prenom ;
	
	private String email ;
	@Column(nullable = false)
	private String mdp;

	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "user_courses",
		        joinColumns = { @JoinColumn(name = "user_id") },
		        inverseJoinColumns = { @JoinColumn(name = "id_cc") })
	    private Set<Course> courses = new HashSet<>();
	
	
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	public SUser(int id, String nom, String prenom,  String email, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}
	public SUser() {}
	@SuppressWarnings("serial")
	public static  class UserNotFoundException extends RuntimeException {

	    public UserNotFoundException(String message) {
	        super(message);
	    }
	}
	@SuppressWarnings("serial")
	public static  class InvalidPasswordException extends RuntimeException {

	    public InvalidPasswordException(String message) {
	        super(message);
	    }
	    
	}
	@SuppressWarnings("serial")
	public static class EmailAlreadyExistsException extends RuntimeException {

	    public EmailAlreadyExistsException(String message) {
	        super(message);
	    }
	}

}

