import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test(dataProvider="BooksData")
	//We have to define how many elements each array is returning.
	public void addBook(String aisle, String isbn) throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
//		String resp = given().header("Content-Type","application/json").body(payload.addBook())
//		.when().post("/Library/Addbook.php")
//		.then().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath js = ReusableMethods.rawToJson(resp);
//		String id = js.get("ID");
//		System.out.println(id);
		
		// The issue with this code is once we added the book details previded in Json, we cannot use the 
		// same code and Json again as it will give error. 
		// So now we need to make it dynamic
		// 1st way is to add delete code with the post code so that as soon as the book is created we 
		// will delete the same.
		// With the 1st way we can also make some of the Json parameters dynamic with the below code:
		
//		String resp = given().header("Content-Type","application/json").body(payload.addBook("6464","asdef"))
//				.when().post("/Library/Addbook.php")
//				.then().assertThat().statusCode(200).extract().response().asString();
//				
//				JsonPath js = ReusableMethods.rawToJson(resp);
//				String id = js.get("ID");
//				System.out.println(id);
		// add delete also
				
		//We can also parametrize the parameters of Json for multiple data as below:
//		String resp = given().header("Content-Type","application/json").body(payload.addBook(isbn,aisle))
//				.when().post("/Library/Addbook.php")
//				.then().assertThat().statusCode(200).extract().response().asString();
//				
//				JsonPath js = ReusableMethods.rawToJson(resp);
//				String id = js.get("ID");
//				System.out.println(id);
		// add delete also
				
		// We can also get the Json Param from the Json file in .json format. We can read that as below
		//1st we need to convert the content of file into String --> First convert into byte and then to String
		String resp = given().header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("/DemoProject/src/Library+API.postman_collection.json"))))
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
				
				JsonPath js = ReusableMethods.rawToJson(resp);
				String id = js.get("ID");
				System.out.println(id);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//array = collection of elements
		//multi dimensional array = collection of arrays
		// if we have 5 arrays then the test will run 5 times each time it will take 1 array.
		return new Object[][] {{"9363","ojfwy"},{"4253","cwette"},{"533","qwgty"}};
	}
}
