package com.plutus.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.plutus.model.Asset;
import com.plutus.model.Bean;
import com.plutus.model.Person;

public class BeanTest {
	
	public static void main(String [] args) {
		
//		List<Person> listp = new ArrayList<>();
//		
//		for (int i=200; i< 5000; i++) {
//			Person p = new Person();
//			p.setType(p.getClass().getSimpleName());
//			p.setAge(i);
//			//p.setId(i+"");
//			p.setFirstName("Name "+i);
//			Map<String,String> addresses = new HashMap<>();
//			Map<String,String> phones = new HashMap<>();
//			for (int j = 0; j < 5; j++) {
//				addresses.put("Type Address "+j, "Casa "+j+" Home"+i);
//				phones.put("Type Phone "+j, "Phone "+j+"-"+i+" "+i*j);
//			}
//			
//			p.setAddresses(addresses);
//			p.setPhones(phones);
//			
//			DAO.insert(p);
//			//insert(p);
//		}
//		
//		for(int k = 3; k < 30000; k++) {
//			Asset a =new Asset();
//			a.setType(a.getClass().getSimpleName());
//			a.setAsset_name("Asset "+k);
//			DAO.insert(a);
//			
//			//insert(a);
//		}
		
		//b810794e-a9cb-413d-ba52-3107b8752e9f  Asset 716
//		310f2609-1acc-4476-bc04-4f9b43129be3  Asset 840
//		76348928-631c-4310-ad1b-f4f1e40bb4b9  Asset 1088

		//Asset a = new Asset();
		
		//a = (Asset) DAO.selectById(a, "76348928-631c-4310-ad1b-f4f1e40bb4b9");
		
		
		//System.out.println(a.getAsset_name());
		//List<Asset> l = (List) DAO.selectList(a, "310");
		
		//l.forEach(x -> System.out.println(x.getId() +"  "+x.getAsset_name()));
		
		//System.out.println(a.getId()+" "+a.getAsset_name());
		
		Person p = new Person();
		
		p = (Person) DAO.select(p, "1-4272 4272");
		
		System.out.println(p.getAge()+" "+ p.getFirstName()+"  "+p.getId());
		
	}

	public static String getId(String json) {
		
		String id = null;
		
		JsonParser parser = new JsonParser();

		JsonElement jsonTree = parser.parse(json);

		if(jsonTree.isJsonObject()){
		    JsonObject jsonObject = jsonTree.getAsJsonObject();

		    JsonElement je = jsonObject.get("id");
		    
		    id = je.getAsString();

//		    JsonElement f2 = jsonObject.get("f2");
//
//		    if(f2.isJsonObject()){
//		        JsonObject f2Obj = f2.getAsJsonObject();
//
//		        JsonElement f3 = f2Obj.get("f3");
//		    }

		}
		return id;
	}
	
}
