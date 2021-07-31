package stepDefinitions;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.serializePojo;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import pojo.Location;

@RunWith(Cucumber.class)
public class stepDefinition extends Utils {
	
	
	 ResponseSpecification respec;
	 RequestSpecification res;
	 Response responseString;
	 String resString;
	 static String place_id;
	 String name;

	 TestDataBuild data = new TestDataBuild();
	 
	 @Given("All request Payload with {int} {string} {string} {string} {string} {string}")
	 public void all_request_payload_with(int accuracy, String address, String language, String name, String phone, String website) throws IOException {
	    		
		//	respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//	Response res = given().queryParam("key", "qaclick123")
			res = given().spec(requestSpecification())
			.body(data.addPlacePayLoad(accuracy, address, language, name, phone, website));
	    }

	 @When("user calls {string}  with {string} http request")
	 public void user_calls_with_http_request(String resource, String method) {
	    	
	    	APIResources resApi = APIResources.valueOf(resource);
	    	if(method.equalsIgnoreCase("POST"))
	    	{
	    	responseString = res.when().post(resApi.getResource());
	    	}
	    	else if(method.equalsIgnoreCase("GET"))
	    	{
	    		responseString = res.when().get(resApi.getResource());
	    	}
	    			
	    			
	    }

	    @Then("^the API call got success with status code 200$")
	    public void the_api_call_got_success_with_status_code_200() {
	    	//responseString.then().spec(respec).extract().response();
	    	assertEquals(responseString.statusCode(), 200);
	    	resString = responseString.asString();
			System.out.println(resString);
			
	    }

	    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String keyValue, String expectedvalue) {
	    	
		//	JsonPath js= new JsonPath(resString);
	    	System.out.println(getJsonPath(responseString, keyValue));
			assertEquals(getJsonPath(responseString, keyValue),expectedvalue);
			
	    }
	    
	    @Then("verify place_id created maps to {string} using {string}")
	    public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	 
	    	place_id= getJsonPath(responseString, "place_id");
	    	//System.out.println(place_id);
	    	res= given().spec(requestSpecification()).queryParam("place_id", place_id);
	    	user_calls_with_http_request(resource, "GET");
	    //	System.out.println(responseString.asString());
	    	name= getJsonPath(responseString, "name");
	    	System.out.println(name);
	        assertEquals(name,expectedname);
	    	
	    }
	    

       @Given("Delete place payload")
       public void delete_place_payload() throws IOException {
    	   res = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
    
     }
	    
	    
	}


