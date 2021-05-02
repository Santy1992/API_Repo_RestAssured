package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		// For more than 1 data, the below code will run only once. Else it would have ran more than once.
		if (req == null) {
			//Adding log to a new file
			PrintStream log = new PrintStream(new FileOutputStream("logging.txr"));
			
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			
			return req;
		}
		return req;
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./resources/global.properties");
		prop.load(file);
		prop.get(key);
		return prop.getProperty(key);
	}
	
	public String getJSONPath(Response response, String key) {
		String resStrn = response.asString();
        JsonPath js = new JsonPath(resStrn);
        return js.get(key).toString();
	}
}
