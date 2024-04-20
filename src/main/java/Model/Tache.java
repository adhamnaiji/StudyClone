package Model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tache")
public class Tache {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_t;
	private Date date ;
	private Time heure_debut ;
	private Time heure_fin ;
	private String status ;
	private String lieu ;
	@ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private User patient;

	@ManyToOne
	@JoinColumn(name="employe_id", nullable=true)
	private User employe;


	public int getId_t() {
		return id_t;
	}


	public void setId_t(int id_t) {
		this.id_t = id_t;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Time getHeure_debut() {
		return heure_debut;
	}


	public void setHeure_debut(Time heure_debut) {
		this.heure_debut = heure_debut;
	}


	public Time getHeure_fin() {
		return heure_fin;
	}


	public void setHeure_fin(Time heure_fin) {
		this.heure_fin = heure_fin;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getLieu() {
		return lieu;
	}


	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public User getPatient() {
		return patient;
	}


	public void setPatient(User patient) {
		this.patient = patient;
	}


	public User getEmploye() {
		return employe;
	}


	public void setEmploye(User employe) {
		this.employe = employe;
	}


	public Tache(int id_t, Date date, Time heure_debut, Time heure_fin, String status, String lieu, User patient,
			User employe) {
		super();
		this.id_t = id_t;
		this.date = date;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.status = status;
		this.lieu = lieu;
		this.patient = patient;
		this.employe = employe;
	}
	public Tache() {}
}




