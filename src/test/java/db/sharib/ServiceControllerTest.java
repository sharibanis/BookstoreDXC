package db.sharib;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
	classes = BookstoreApplication.class
)
public class ServiceControllerTest {
	private static final Logger log = LoggerFactory.getLogger(ServiceControllerTest.class);
	private static final String baseUri = "http://localhost:8080/bookstore"; 
	private final String PASS = ""; 
    
	@BeforeAll
	public static void setUp() throws Exception {
		log.info("Starting tests...");
		RestAssured.baseURI = baseUri;
	}
	
	@Test
	public void whenRequestGet_thenOK() {
    	log.info("whenRequestGet_thenOK()");
		Response response = RestAssured.get("/find");
		response.then().assertThat().statusCode(200);
		//response.then().assertThat().body("some.property", equalTo("expected value"));
	}
	
	@Test
	public void whenRequestGet_thenNotOK() {
    	log.info("whenRequestGet_thenNotOK()");
	}
	
	@AfterAll
	public static void cleanUp(){
		log.info("@AfterAll cleanUp() method called");
	}
}
