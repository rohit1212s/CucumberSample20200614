package com.stepdefinition;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import static org.junit.Assert.assertThat;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.json.JsonSlurper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import requestModel.CreatePet;

public class CreateNewPost {
	
	static RequestSpecification requestSpec;
	static Response response;
	CreatePet pet = new CreatePet();
	
	@Given("^user is using the baseURI$")
	public void user_is_using_the_baseURI() throws Throwable {
		
		RequestSpecification request = RestAssured.with();
		 requestSpec = request.given().contentType(ContentType.JSON).baseUri("http://petstore.swagger.io/v2/");
	    
	}

	@When("^the user makes the post call to the endpoint \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_user_makes_the_post_call_to_the_endpoint_with_and(String endpoint, String name, String status) throws Throwable {    
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		pet.setName(name);
		pet.setStatus(status);
	   response = requestSpec.body(pet).headers(headers).post(endpoint);
	   System.out.println(response.getBody().asString());
	   long id1 = response.then().extract().path("id");
		
	}
	
	@When("^the user makes the put call to the endpoint \"([^\"]*)\" with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_user_makes_the_put_call_to_the_endpoint_with_and(String endpoint, String id, String name, String status) throws Throwable {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		pet.setId(Long.parseLong(id));
		pet.setName(name);
		pet.setStatus(status);
		response = requestSpec.body(pet).headers(headers).put(endpoint);
	   System.out.println(response.getBody().asString());
	   
	}
	
	@When("^the user makes the delete call to the endpoint \"([^\"]*)\" with \"([^\"]*)\"$")
	public void the_user_makes_the_delete_call_to_the_endpoint_with(String endpoint, String id) throws Throwable {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		pet.setId(Long.parseLong(id));
		response = requestSpec.body(pet).headers(headers).delete(endpoint+id);
	 
	}
	
	@Then("^user validates the message after a pet is deleted$")
	public void validates_the_message_after_a_pet_is_deleted() throws Throwable {
		Assert.assertEquals("Pet not found", response.then().extract().path("message"));
	}

	@Then("^user should get a response code: (\\d+)$")
	public void user_need_to_get_a_response_code(int code) throws Throwable {
		
		Assert.assertEquals(code, response.getStatusCode());
		//Log4JLogger.log("march batch execution"+response.getStatusCode());
	    
	}

	@When("^user retrieves the pet details \"([^\"]*)\" with \"([^\"]*)\"$")
	public void user_retrive_order_details(String endpoint,String id) throws Throwable {		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		 response = requestSpec.headers(headers).get(endpoint+id); 
	}
	
	
	@When("^ the user uploads an image")
	public void user_uploads_an_image(String endpoint,String id) throws Throwable {	
		
		//File filename = new File(file);
		
		//HashMap<String, String> headers = new HashMap<String, String>();
		//headers.put("Content-Type", "multipart/form-data");
	    //headers.put("file", "Instructor1");
	    //headers.put("file_type", "Web");
		//response = requestSpec.headers(headers).post(endpoint+id); 
	    RestAssured.given()
        .header(new Header("content-type", "multipart/form-data"))
        .multiPart("file",new File( "./src/main/resources/test.txt"))
        .formParam("petId", "74865486549594");
		
		
		
		/*Response response = given()
				.baseUri("http://petstore.swagger.io/v2/")
				.basePath("pet/74865486549594/uploadImage")
				.multiPart("file", filepath, "file")
				.multiPart("petId", "74865486549594", "text")
				.post();
		System.out.println("Response of upload is"+response);*/
	}
	

	@When("^validates the details$")
	public void validate_the_details() throws Throwable {
		String text = response.getBody().asString();
		System.out.println("get method :"+text);	
		Assert.assertEquals(pet.getId(), response.then().extract().path("id"));
		Assert.assertEquals(pet.getStatus(), response.then().extract().path("status"));
	}
 



}
