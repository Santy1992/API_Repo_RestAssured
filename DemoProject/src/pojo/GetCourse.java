package pojo;

public class GetCourse {
	
	private String url;
	private String services;
	private String expertise;
	private Courses Courses; // Changing the reutn type from String to class as we are inserting child POJO class.
	private String Instructor;
	private String linkedIn;
	
	// As Courses variable is not simple JSON String Value pair, its a nested JSON where values of Courses are another JSON,Its in curly brackets
	// In this case we need to create a another POJO class which will be for the child JSON.
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public pojo.Courses getCourses() {
		return Courses;
	}
	public void setCourses(pojo.Courses courses) {
		Courses = courses;
	}
	public String getInstructor() {
		return Instructor;
	}
	public void setInstructor(String instructor) {
		Instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}
