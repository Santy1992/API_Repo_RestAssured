package pojo;

import java.util.List;

public class Courses {
	
	private List<WebAutomationCourse> webAutomation;
	private List<ApiCourse> api;
	private List<MobileCourse> mobile;
	
	// The values of this POJO class is a array. Inside the array are the JSON values.
	// So we need to retrive the values for Array.
	// We will create seperate child classes.
	//Then we will be injecting other child classes here.
	//If the return was not an array and just a simple Json then no need to use List<>.
	
	public List<WebAutomationCourse> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomationCourse> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<ApiCourse> getApi() {
		return api;
	}
	public void setApi(List<ApiCourse> api) {
		this.api = api;
	}
	public List<MobileCourse> getMobile() {
		return mobile;
	}
	public void setMobile(List<MobileCourse> mobile) {
		this.mobile = mobile;
	}
		
}
