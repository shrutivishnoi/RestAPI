package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.serializePojo;

public class TestDataBuild {
	
	
	public serializePojo addPlacePayLoad(int accuracy, String address, String language, String name, String phone, String website)
	{
	serializePojo serializeP = new serializePojo();
	serializeP.setAccuracy(accuracy);
	serializeP.setAddress(address);
	serializeP.setLanguage(language);
	serializeP.setName(name);
	serializeP.setPhone_number(phone);
	serializeP.setWebsite(website);
	
   List<String> list1= new ArrayList<String>();
   list1.add("shoe park");
   list1.add("shop");
   
	serializeP.setTypes(list1);
	
	Location loc= new Location();
	loc.setLat(-38.383494);
	loc.setLng(33.427362);
	serializeP.setLocation(loc);
	
	return serializeP;
	}
	
	public String deletePlacePayLoad(String placeid)
	{
		return "{\n"
				+ "    \"place_id\":\""+placeid +"\"\n"
				+ "}";
	}

}
