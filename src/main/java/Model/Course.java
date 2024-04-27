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
	private String price;
	private String courseUrl;
	
	
	

	public Course(String title, String description, SUser user) {
		super();
		this.title = title;
		this.description = description;
		this.user = user;
	}
	
	public String getCourseUrl() {
		return courseUrl;
	}
	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}
	public SUser getUser() {
		return user;
	}
	public void setUser(SUser user) {
		this.user = user;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private Set<SUser> users = new HashSet<>();
	
	@JsonIgnore
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
	
	
	public Course() {}
	
	@SuppressWarnings("serial")
	public static  class CourseNotFoundException extends RuntimeException {

	    public CourseNotFoundException(String message) {
	        super(message);
	    }
	}
	
	

}
