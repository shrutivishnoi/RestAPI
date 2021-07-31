package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefinition sd = new stepDefinition();
		if(stepDefinition.place_id==null)
		{
		sd.all_request_payload_with(20, "gurgaon", "En", "shruti", "876554567897", "bsvd@swg.dh");
		sd.user_calls_with_http_request("addPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("shruti", "getPlaceAPI");
		}
	}
	

}
