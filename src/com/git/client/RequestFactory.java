package com.git.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RequestFactory {
	static public WebResource createWebResource(String URL, String userName, String password) {
		Client client = Client.create();
		
		HTTPBasicAuthFilter authFilter = new HTTPBasicAuthFilter(userName, password);
		client.addFilter(authFilter);
		
		WebResource webResource = client.resource(URL);
		webResource.accept("application/json");

		return webResource;
	
	}
	
	static public WebResource createWebResource(String URL) {
		Client client = Client.create();
		
		WebResource webResource = client.resource(URL);
		webResource.accept("application/json");

		return webResource;
	
	}
}
