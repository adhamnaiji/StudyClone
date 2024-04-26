package Model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_c;
	private String title ;
	private String description ;
	
	
	

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private Set<SUser> users = new HashSet<>();
	
	
	@ManyToOne
    @JoinColumn(name = "user_id_")
    private SUser user;
	
	

	
	
	public Set<SUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SUser> users) {
		this.users = users;
	}
	public int getId_c() {
		return id_c;
	}
	public void setId_c(int id_c) {
		this.id_c = id_c;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Course(int id_c, String title, String description) {
		super();
		this.id_c = id_c;
		this.title = title;
		this.description = description;
	}
	public Course() {
		super();
	}
	
	

}
