package pojo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;

public class Deserialization {

	public static void main(String[] args) {
		// Here we need to call the Pojo class.
		// Also we need to specify what kind of respose we are expecting either JSON or XML. If we have
		//given the Content_Type as application_json then no need to give defaultParser as Json
		GetCourse gc = given().queryParam("access_token", "xyz").expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php")
				.as(GetCourse.class);
		
		// To get the values of the variables of the JSON response
		String linkedInValue = gc.getLinkedIn();
		String instructorValue = gc.getInstructor();
		
		//To get the title of a course present inside API course
		String APICourse1Title = gc.getCourses().getApi().get(1).getCourseTitle();
		
		//To get the price of a course present inside API course
		List<ApiCourse> apiCourses = gc.getCourses().getApi();
		for(int i=0;i<=apiCourses.size();i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SOAP UI WebServices Testing")) {
				String priceValue = apiCourses.get(i).getPrice();
			}
		}
		
		// To get all course titles for WebAutomation couse
		List<WebAutomationCourse> webAutomationCourses = gc.getCourses().getWebAutomation();
		for (int c=0;c<=webAutomationCourses.size();c++) {
			String webAutomationTitile = webAutomationCourses.get(c).getCourseTitle();
			System.out.println(webAutomationTitile);
		}
		
		// To va;idate the Web Automation course titles
		String[] webAutomationExpected = {"Selenium Web Driver Java","Cypress","Protactor"};
		List<WebAutomationCourse> webAutomationCourses2 = gc.getCourses().getWebAutomation();
		ArrayList<String> a = new ArrayList(); // We are using ArrayList intead of Array as we can increase the size on demand.
		for (int c=0;c<=webAutomationCourses2.size();c++) {
			a.add(webAutomationCourses2.get(c).getCourseTitle());
		}
		//Its easy to compare Arraylist and Arraylist instead of Array and arraylist
		List<String> expectedTitle = Arrays.asList(webAutomationExpected);
		//Validation
		Assert.assertTrue(a.equals(expectedTitle));		
	}

}
