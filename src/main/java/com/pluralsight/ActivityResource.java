package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("activities") //http://localhost:8080/exercise-services/webapi/activities
public class ActivityResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@POST
	@Path("activity")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Activity createActivity(Activity activity) {
		
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	@POST
	@Path("activity")	
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
		Activity activity= new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(formParams.getFirst("duration"));
		
		activityRepository.create(activity);
		
		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));
		return activity;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) // XML -> JSON
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActivities();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) // XML -> JSON
	@Path("{activityId}") ////http://localhost:8080/exercise-services/webapi/activities/1234
	public Activity getActivity(@PathParam ("activityId") String activityId) {
		return activityRepository.findActivity(activityId);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) // XML -> JSON
	@Path("{activityId}/user") ////http://localhost:8080/exercise-services/webapi/activities/1234/user
	public User getActivityUser(@PathParam ("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}
}
