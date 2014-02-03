package com.pluralsight.client;

import static org.junit.Assert.assertNotNull; // static import - not added by shift-cmd-o

import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;

public class ActivityClientTest {

	@Test
	public void testCreate() {
		ActivityClient client = new ActivityClient();
		Activity activity = new Activity();
		activity.setDescription("Swimming");
		activity.setDuration("90");
		activity = client.create(activity);
		assertNotNull(activity);
	}
	
	@Test
	public void testGet() {
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("1234");
		System.out.println(activity);
		assertNotNull(activity);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithBadRequest() {
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("123"); // requires at least length of 4 for id
		assertNotNull(activity);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithNotFound() {
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("7777"); // this particular id returns null
		assertNotNull(activity);
	}

	@Test
	public void testGetList() {
		ActivityClient client = new ActivityClient();
		List<Activity> activities = client.get();
		
		assertNotNull(activities);
	}
}
