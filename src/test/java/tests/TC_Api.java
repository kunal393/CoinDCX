package tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class TC_Api{

	
//	@BeforeTest
//	public void precondition_api() throws IOException {
//		
//	}
	
	@Test
	public void test_api() throws IOException {
		RestAssured.baseURI="http://coindcx.com";
		given().log().all().when().post("/api/v3/authenticate").then().log().all().assertThat().statusCode(200);
	}
	
}
