package test.com.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.git.client.RequestFactory;
import com.git.client.util.ResponseUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AnithaUserTest {

	static final String USER_NAME = "asalavadi";

	static final String PASSWORD = "***************";

	@Before
	public void setup() {

	}

	@Test
	public void testGetUserDetailsSuccess() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/users/asalavadi");
		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 200);

		Map<String, Object> anithaUserDetails = ResponseUtil.convertToMap(response);
		Assert.assertEquals(anithaUserDetails.get("login"), "Asalavadi");
		Assert.assertTrue((int) anithaUserDetails.get("public_repos") > 1);
	}

	@Test
	public void testGetReposListSuccess() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/users/Asalavadi/repos",
				USER_NAME, PASSWORD);
		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 200);

		List<Map<String, Object>> anithaReposList = ResponseUtil.convertToList(response);
		Assert.assertTrue(anithaReposList.size() >= 2);

		List repoList = new ArrayList();
		repoList.add("GitTest");
		repoList.add("TestTutorial");
		repoList.contains(anithaReposList.get(0).get("name"));
		repoList.contains(anithaReposList.get(1).get("name"));
	}

	@Test
	public void testGetCurrentUserWithBasicAuthSuccess() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/user", USER_NAME,
				PASSWORD);

		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 200);
	}
	
	@Test
	public void testGetCurrentUserWithBasicAuthFail() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/user", USER_NAME,
				"pass");

		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 401);
	}
	
	@Test
	public void test404Fail() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/users/Asalavadi/repo");

		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 404);
	}
}
