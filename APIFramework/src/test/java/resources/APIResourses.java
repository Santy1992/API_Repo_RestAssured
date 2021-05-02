package resources;

//Enum is a special class in java which has collection of constants and methods
public enum APIResourses {
	
	//Should be same as mentioned in feature file
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	//The parameter for the constructor should be defined same as the parameter in the above methods
	APIResourses(String resource){
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
	
}
