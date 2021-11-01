package com.ct.admin.testservice;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//@SpringBootTest
public class TestAdminService {

	/*
	 * @Mock private RestTemplate restTemplate;
	 * 
	 * @InjectMocks private AdminServiceImpl adminService; private
	 * MockRestServiceServer mockServer; private ObjectMapper mapper = new
	 * ObjectMapper();
	 * 
	 * @Before public void init() { //restTemplate = new RestTemplate(); mockServer
	 * = MockRestServiceServer.createServer(restTemplate); }
	 * 
	 * @Test public void testGetAllUsers() throws JsonProcessingException { Staff
	 * staff = new Staff(); staff.setUserId(101L); staff.setFirstName("TestName");
	 * staff.setLastName("TestLASTNAME"); staff.setEmail("test@test.com");
	 * Map<String, Object> response = new HashMap<>(); response.put("staffs",
	 * staff); URI targetUrl =
	 * UriComponentsBuilder.fromUriString("http://USER-SERVICE/users/")
	 * .path("employees/user-list") .queryParam("page", 0) .queryParam("size",5)
	 * .queryParam("columnName","userId") .queryParam("direction", "ASC") .build()
	 * .toUri(); mockServer.expect(ExpectedCount.once(), requestTo(targetUrl))
	 * .andExpect(method(HttpMethod.GET)) .andRespond(withStatus(HttpStatus.OK)
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .body(mapper.writeValueAsString(response)));
	 * 
	 * 
	 * Map<String, Object> testObj = adminService.getAllEmployee();
	 * mockServer.verify(); org.junit.Assert.assertEquals(response, testObj);
	 * 
	 * 
	 * }
	 */
}
