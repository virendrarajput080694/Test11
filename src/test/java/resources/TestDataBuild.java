package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlaces;
import POJO.Locations;

public class TestDataBuild {
	
	public AddPlaces addplacePayload(String name, String language, String address)
	{
		AddPlaces p = new AddPlaces();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);
		
		Locations l = new Locations();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String placeid)
	{
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}

}
