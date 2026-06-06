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
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
	classes = BookstoreApplication.class
)
public class ServiceControllerTest {
	private static final Logger log = LoggerFactory.getLogger(ServiceControllerTest.class);
	private static final String baseUri = "http://localhost:8080/"; 
	private final String PASS = ""; 
    
	@BeforeAll
	public static void setUp() throws Exception {
		log.info("Starting tests...");
		RestAssured.baseURI = baseUri;
	}
	
	@Test
	public void whenRequestHealth_thenOK() {
    	log.info("whenRequestHealth_thenOK()");
		given().log().uri().when().get("/actuator/health");
		Response response = RestAssured.get("/actuator/health");
		response.then().assertThat().statusCode(200).log().all();
	}

	@Test
	public void whenRequestGetFind_thenOK() {
    	log.info("whenRequestGetFind_thenOK()");
		given().log().uri().when().get("/bookstore/public/find");
		Response response = RestAssured.get("/bookstore/public/find?title=SomeTitle&author=SomeAuthor");
		response.then().assertThat().statusCode(200).log().all();
		//response.then().assertThat().body("some.property", equalTo("expected value"));
	}
	
	@Test
	public void whenRequestGetFind_thenNotOK() {
    	log.info("whenRequestGetFind_thenNotOK()");
	}
	
	@Test
	public void whenRequestAddBook_thenOK() {
    	log.info("whenRequestAddBook_thenOK()");
		try {
			JSONObject requestBody = new JSONObject(); 
			requestBody.put("ISBN", 1234567890123L);
			requestBody.put("title", "Test Book");
			requestBody.put("authors", "Test Author");
			requestBody.put("yearPublished", 2024);
			requestBody.put("price", 19.99);
			requestBody.put("genre", "Test Genre");
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			request.body(requestBody.toString()); 
			request.auth()
			.basic("user", "password")
			.when()
			.post("/bookstore/addBook")
			.then()
			.assertThat()
			.statusCode(HttpStatus.OK.value());
			log.info("Book {} added successfully.", "Test Book");
		} catch (Exception ex) {
			log.warn("Exception occurred while adding book {}. ", 
				"Test Book", ex.toString());
		}
	}
	
	@AfterAll
	public static void cleanUp(){
		log.info("@AfterAll cleanUp() method called");
	}
}
