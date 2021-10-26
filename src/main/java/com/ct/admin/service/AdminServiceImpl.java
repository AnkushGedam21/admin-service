package com.ct.admin.service;



import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.exceptions.ServerNotAvailableExceptions;

import io.github.resilience4j.retry.annotation.Retry;


/* 
 * author Ankush Gedam
 * This is Admin Service 
 which will take care of
 all the acticity of admin service
 this class is implementation of adminservice
 */

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private Logger log;
	@Autowired
	private RestTemplate restTemplate;
	String userServiceURL = "http://USER-SERVICE/";

	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getAllUsers(int page, int size,String columnName,String direction){
		log.info("from service all user data send");

		ParameterizedTypeReference<PaginatedResponse<Staff>> responseType = new ParameterizedTypeReference<PaginatedResponse<Staff>>() { };
		URI targetUrl = UriComponentsBuilder.fromUriString(userServiceURL)
				.path("employees/user-list")
				.queryParam("page", page)
				.queryParam("size",size)
				.queryParam("columnName",columnName)
				.queryParam("direction", direction)
				.build()
				.toUri();

		ResponseEntity<PaginatedResponse<Staff>> result = restTemplate.exchange(targetUrl, HttpMethod.GET,null,responseType);
		Page<Staff> pageStaff = result.getBody();
		Map<String, Object> response = new HashMap<>();
		response.put("staffs", pageStaff.getContent());
		response.put("currentPage", pageStaff.getNumber());
		response.put("totalItems", pageStaff.getTotalElements());
		response.put("totalPages", pageStaff.getTotalPages());
		response.put("last", pageStaff.isLast());
		response.put("first", pageStaff.isFirst());
		response.put("sort", pageStaff.getSort());
		response.put("numberOfElements", pageStaff.getNumberOfElements());
		response.put("number", pageStaff.getNumber());
		response.put("empty", pageStaff.isEmpty());
		response.put("totalElements", pageStaff.getTotalElements());
		response.put("page", pageStaff.getNumber());
		response.put("size", pageStaff.getSize());

		return response;
	}

	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getAllPatient(int page,int size,String columnName,String direction){
		log.info("from service all patient details send");
		ParameterizedTypeReference<PaginatedResponse<Patient>> responseType = new ParameterizedTypeReference<PaginatedResponse<Patient>>() { };
		URI targetUrl = UriComponentsBuilder.fromUriString(userServiceURL)
				.path("patient/patient-list")
				.queryParam("page", page)
				.queryParam("size", size)
				.queryParam("columnName",columnName)
				.queryParam("direction", direction)
				.build()
				.toUri();
		ResponseEntity<PaginatedResponse<Patient>> result = restTemplate.exchange(targetUrl, HttpMethod.GET,null,responseType);
		List<Patient> patientList = result.getBody().getContent();    //http://localhost:8082/filteredpatients?page=1&size=2
		Map<String, Object> response = new HashMap<>();
		Page<Patient> pagePatient= result.getBody();
		response.put("patients", patientList);
		response.put("currentPage", pagePatient.getNumber());
		response.put("totalItems", pagePatient.getTotalElements());
		response.put("totalPages", pagePatient.getTotalPages());
		response.put("last", pagePatient.isLast());
		response.put("first", pagePatient.isFirst());
		response.put("sort", pagePatient.getSort());
		response.put("numberOfElements", pagePatient.getNumberOfElements());
		response.put("number", pagePatient.getNumber());
		response.put("empty", pagePatient.isEmpty());
		response.put("totalElements", pagePatient.getTotalElements());
		response.put("page", pagePatient.getNumber());
		response.put("size", pagePatient.getSize());
		return response;
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getPatientCount() {
		log.info("Inside Admin Service Mehod to edit patient status");
		Long[] count = Optional.of(restTemplate.getForObject(userServiceURL+"/patients/patientcount", Long[].class)).orElseThrow(()-> new ServerNotAvailableExceptions());
		
		/*
		 * Supplier<Long[]> count= ()->
		 * restTemplate.getForObject(userServiceURL+"/patients/patientcount",
		 * Long[].class); RetryConfig config = RetryConfig.ofDefaults(); RetryRegistry
		 * registry = RetryRegistry.of(config); io.github.resilience4j.retry.Retry retry
		 * = registry.retry("flightSearchService", config); Supplier<Long[]> retrycount
		 * = io.github.resilience4j.retry.Retry.decorateSupplier(retry, count);
		 */
		
		Map<String, Object> response = new HashMap<>();
		response.put("count", count);
		return response;
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getStaffCount(){
		log.info("Inside Admin Service Mehod to get employee count");
		Map<String, Object> response = new HashMap<>();
	
			Long[] count = restTemplate.getForObject(userServiceURL+"employee/employeecount", Long[].class);
			response.put("count", count);
			return response;
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> editPatientStatus(List<Patient> allPatient)  {
		log.info("Inside Admin Service Mehod to edit Patient status");
		Map<String, Object> response = new HashMap<>();
		restTemplate.put(userServiceURL+"patient/editstatus", allPatient);
		String msg="Patient Status has been Edited";
		response.put("msg", msg);
		return response;
	}
	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> editEmployeeStatus(List<Staff> allEmployee) {
		log.info("Inside Admin Service Mehod to edit employee status");
		Map<String, Object> response = new HashMap<>();
		restTemplate.put(userServiceURL+"employee/editstatus", allEmployee);
		String msg="Employee Status has been Edited";
		response.put("msg", msg);
		return response;
	}	
	

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getFilteredStaff(int page, int size,String direction,String filterValue) {
		ParameterizedTypeReference<PaginatedResponse<Staff>> responseType = new ParameterizedTypeReference<PaginatedResponse<Staff>>() { };
		URI targetUrl = UriComponentsBuilder.fromUriString(userServiceURL)
				.path("employees/filteredstaff")
				.queryParam("page", page)
				.queryParam("size",size)
				.queryParam("direction", direction)
				.queryParam("filterValue", filterValue)
				.build()
				.toUri();

		ResponseEntity<PaginatedResponse<Staff>> result = restTemplate.exchange(targetUrl, HttpMethod.GET,null,responseType);
		Page<Staff> pageStaff = result.getBody();
		Map<String, Object> response = new HashMap<>();
		response.put("staffs", pageStaff.getContent());
		response.put("currentPage", pageStaff.getNumber());
		response.put("totalItems", pageStaff.getTotalElements());
		response.put("totalPages", pageStaff.getTotalPages());
		response.put("last", pageStaff.isLast());
		response.put("first", pageStaff.isFirst());
		response.put("sort", pageStaff.getSort());
		response.put("numberOfElements", pageStaff.getNumberOfElements());
		response.put("number", pageStaff.getNumber());
		response.put("empty", pageStaff.isEmpty());
		response.put("totalElements", pageStaff.getTotalElements());
		response.put("page", pageStaff.getNumber());
		response.put("size", pageStaff.getSize());

		return response;

		
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getFilteredPatient(int page, int size,String direction,String filterValue) {
		log.info("from service all patient details send");
		ParameterizedTypeReference<PaginatedResponse<Patient>> responseType = new ParameterizedTypeReference<PaginatedResponse<Patient>>() { };
		URI targetUrl = UriComponentsBuilder.fromUriString(userServiceURL)
				.path("patient/filteredpatients")
				.queryParam("page", page)
				.queryParam("size",size)
				.queryParam("direction", direction)
				.queryParam("filterValue", filterValue)
				.build()
				.toUri();
		ResponseEntity<PaginatedResponse<Patient>> result = restTemplate.exchange(targetUrl, HttpMethod.GET,null,responseType);
		List<Patient> patientList = result.getBody().getContent();    //http://localhost:8082/filteredpatients?page=1&size=2
		Map<String, Object> response = new HashMap<>();
		Page<Patient> pagePatient= result.getBody();
		response.put("patients", patientList);
		response.put("currentPage", pagePatient.getNumber());
		response.put("totalItems", pagePatient.getTotalElements());
		response.put("totalPages", pagePatient.getTotalPages());
		response.put("last", pagePatient.isLast());
		response.put("first", pagePatient.isFirst());
		response.put("sort", pagePatient.getSort());
		response.put("numberOfElements", pagePatient.getNumberOfElements());
		response.put("number", pagePatient.getNumber());
		response.put("empty", pagePatient.isEmpty());
		response.put("totalElements", pagePatient.getTotalElements());
		response.put("page", pagePatient.getNumber());
		response.put("size", pagePatient.getSize());
		return response;
	}

	@Override
	//@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getAllPatient() {
		log.info("Inside Admin Service Mehod to get all patient email");
		Map<String, Object> response = new HashMap<>();  
			List<Patient> patients = Arrays.asList(restTemplate.getForObject(userServiceURL+"patients",Patient[].class));
			response.put("patients", patients);
			return response;
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getAllActivePatient() {
		log.info("Inside Admin Service Mehod to get all active patient email");
		Map<String, Object> response = new HashMap<>();
	
		List<Patient> activePatients =Arrays.asList( restTemplate.getForObject(userServiceURL+"patient/allactivepatient", Patient[].class));
		response.put("activePatients", activePatients);
			return response;
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getAllEmployee() {
		log.info("Inside Admin Service Mehod to get all employee email");
		Map<String, Object> response = new HashMap<>();
	
		List<Staff> staffs = Arrays.asList(restTemplate.getForObject(userServiceURL+"employees", Staff[].class));
		response.put("staffs",staffs);
			return response;
	}

	@Override
	@Retry(name = "user",fallbackMethod = "fallbackCall")
	public Map<String, Object> getAllActiveEmployee() {
		log.info("Inside Admin Service Mehod to get active email");
		Map<String, Object> response = new HashMap<>();
		List<Staff> activeStaffs = Arrays.asList(restTemplate.getForObject(userServiceURL+"employees/allactiveemployees",Staff[].class));
		response.put("activeStaffs", activeStaffs);
			return response;
	}
	
	public Map<String, Object> fallbackCall( Exception e){
		log.info("Exception  called");
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("error", e.getMessage());
		response.put("class", e.getClass());
		return response;
	}

}
