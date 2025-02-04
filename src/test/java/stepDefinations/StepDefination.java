package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

public class StepDefination extends Utility {
	
	ResponseSpecification respec;
	RequestSpecification reqSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		reqSpec = given().spec(requestSpecification())
		.body(data.addplacePayload(name,language,address));
	}
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		// constructor will be called with the value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("Post"))
			response = reqSpec.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(resourceAPI.getResource());

	}
	
	
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	 
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {
	  
		
		assertEquals(getJsonPath(response,KeyValue),ExpectedValue);
		
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		place_id = getJsonPath(response, "place_id");
		System.out.println("Place ID: " + place_id);
		reqSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("deletePlace payload")
	public void delete_place_payload() throws IOException {
	  
		reqSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}
	

}
