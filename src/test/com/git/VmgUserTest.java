package test.com.git;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.git.client.RequestFactory;
import com.git.client.util.ResponseUtil;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class VmgUserTest {
	@Test
	public void testGetUserDetailsSuccess() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/repos/vmg/redcarpet/issues?state=closed");
		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 200);
		
		List<Map<String, Object>> closedIssuesList = ResponseUtil.convertToList(response);
		Assert.assertTrue(closedIssuesList.size() >= 1);
	}
	
	@Test
	public void testGetUserDetailsInvalidStateFail() {
		WebResource webResource = RequestFactory.createWebResource("https://api.github.com/repos/vmg/redcarpet/issues?state=inprogress");
		ClientResponse response = webResource.get(ClientResponse.class);
		Assert.assertEquals(response.getStatus(), 422);
	}
}
