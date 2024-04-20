package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom ;
	private String prenom ;
	private String telephone ;
	private String email ;
	@Column(nullable = false)
	private String mdp;
	private String type;
	private int age ;
	private String localisation ;
	private String specialite ;
	private String disponibilite ;
	
	
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}
	public User(int id, String nom, String prenom, String telephone, String email, String mdp, String type, int age,
			String localisation, String specialite, String disponibilite) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
		this.type = type;
		this.age = age;
		this.localisation = localisation;
		this.specialite = specialite;
		this.disponibilite = disponibilite;
	}
	public User() {}
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
