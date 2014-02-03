package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;

public class ActivityClient {
	
	private Client client;
	
	public ActivityClient() {
		client = ClientBuilder.newClient();
	}

	public Activity get(String id) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		// Send a GET request
		// Activity.class specifies the return type of the response. We can have String.class to just debug and see the actual response
		// Activity response = target.path("activities/" + id).request().get(Activity.class); //XML by default
		// Activity response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(Activity.class); //for JSON
		
		// Send a GET request
		Response response = target.path("activities/" + id).request().get(Response.class); //XML by default
		
		if (response.getStatus() != 200) {
			System.out.println(response.getStatus() + ": there was an error on the server.");
		}
		return response.readEntity(Activity.class);
	}
	
	public List<Activity> get() {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		// Generics are handled differently in Jersey
		List<Activity> response = target.path("activities/").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {}); //XML by default

		return response;
	}

	public Activity create(Activity activity) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/activity").request(MediaType.APPLICATION_JSON).post(Entity.entity(activity, MediaType.APPLICATION_JSON)); 
		
		if (response.getStatus() != 200) {
			System.out.println(response.getStatus() + ": there was an error on the server.");
		}
		return response.readEntity(Activity.class);
	}
}
