import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrise()); 
		
		//Print no. of cources count
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchase amount
		int totalAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmt);
		
		//Print title of first course
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		
		//Print all course title and their respective prices
		for(int i=0;i<count;i++) {
			String title = js.get("course["+i+"].title");
			System.out.println(title);
			
			int price = js.getInt("course["+i+"].price");
			System.out.println(price);
		}
		
		//Print no. of copies sold by RPA Course
		for(int i=0;i<count;i++) {
			String title = js.get("course["+i+"].title");
			if (title.equalsIgnoreCase("RPA")) {
				int copies = js.get("course["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
	}

}
