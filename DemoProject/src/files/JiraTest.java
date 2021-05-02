package files;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://jira.example.com:8090";
		//Login Senario - relaxedHTTPSValidation() will bypass any HTTPS Cerfification issue
		//Session will be used for authentication values.
		SessionFilter session = new SessionFilter(); // To record the session response instead of using json path
		String reponse = given().relaxedHTTPSValidation().header("Content-Type","application/json").body("{ \"username\": \"myuser\", \"password\": \"mypassword\" }").log().all().filter(session)
		.when()
		.post("/jira/rest/auth/1/session")
		.then().extract().response().asString();
		
		
		//Add Comment
		String addComment = "How are you";
		String addCommentResponse = given().pathParam("key", "10101").log().all().header("Content-Type","application/json").body("{\r\n" + 
				"  \"visibility\": {\r\n" + 
				"    \"type\": \"role\",\r\n" + 
				"    \"value\": \"Administrators\"\r\n" + 
				"  },\r\n" + 
				"  \"body\": {\r\n" + 
				"    \"type\": \"doc\",\r\n" + 
				"    \"version\": 1,\r\n" + 
				"    \"content\": [\r\n" + 
				"      {\r\n" + 
				"        \"type\": \"paragraph\",\r\n" + 
				"        \"content\": [\r\n" + 
				"          {\r\n" + 
				"            \"text\": \""+addComment+"\",\r\n" + 
				"            \"type\": \"text\"\r\n" + 
				"          }\r\n" + 
				"        ]\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  }\r\n" + 
				"}").filter(session)
		.when()
		.post("/rest/api/3/issue/{key}/comment")
		.then().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(addCommentResponse);
		String commentId = js.get("id");
		
		//Add Attachment
		given().header("X-Atlassian-Token","no-check").pathParam("key", "10101").filter(session).multiPart("file",new File("jira.txt")).header("Content-Type","multipart/form-data")
		.when()
		.post("/rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200); // If we are not creating file object then java will take it as simple string.
		
		//Get issue details - This will return all the field details by default
//		String issueDetails = given().log().all().filter(session).pathParam("key", "10101")
//		.when()
//		.get("/rest/api/3/issue/{key}")
//		.then().log().all().extract().response().asString();
		// To return specific field details - comments field
		String issueDetails = given().log().all().filter(session).pathParam("key", "10101").queryParam("fields", "comment")
				.when()
				.get("/rest/api/3/issue/{key}")
				.then().log().all().extract().response().asString();
		//To verify the comment returned is correct
		JsonPath js1 = new JsonPath(issueDetails);
		int commentsCount = js1.get("fields.comment.comments.size");
		for(int i=0;i<commentsCount;i++) {
			String commentIdIssue = js1.get("fields.comment.comments["+i+"].id").toString();
			if (commentIdIssue.equalsIgnoreCase(commentId)) {
				String bodyIssue = js1.get("fields.comment.comments["+i+"].body").toString();
				Assert.assertEquals(bodyIssue, addComment);
			}
		}
	}

}
