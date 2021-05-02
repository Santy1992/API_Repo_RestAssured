import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//To get Code
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("xyz.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("xyz");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		//String url = driver.getCurrentUrl();
		
		// As in 2020 Google has updated their security and now we cannot use automation to get the code URL.
		// Hence we have to manually navigate till the URL and copy it
		String url = "www.xyz.xom/tttmckskd";
		// We can ask the developer to increase the scope of the url and then no need to change the url everytime your run.
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&Scope")[0];
		System.out.println(code);
		
		//To get the Access Token - Encoding should be false else special will be encoded to any value.
		String accessTokenResponse = given().urlEncodingEnabled(false)
				.queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		String response = given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		
		System.out.println(response);
		
		
	}

}
