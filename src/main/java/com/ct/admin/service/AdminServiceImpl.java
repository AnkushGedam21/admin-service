package com.ct.admin.service;


import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.admin.utility.UserDto;

import lombok.extern.slf4j.Slf4j;

/* 
 * author Ankush Gedam
 * This is Admin Service 
 which will take care of
 all the acticity of admin service
 this class is implementation of adminservice
 */

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private RestTemplate restTemplate;
	String userServiceURL = "http://USER-SERVICE/";
	public Map<String, Object> getAllUsers(int page, int size,String columnName,String direction){
		log.info("from service all user data send");
		
		ParameterizedTypeReference<PaginatedResponse<Staff>> responseType = new ParameterizedTypeReference<PaginatedResponse<Staff>>() { };
		URI targetUrl = UriComponentsBuilder.fromUriString(userServiceURL)
				.path("filteredemployees")
				.queryParam("page", page)
				.queryParam("size",size)
				.queryParam("columnName",columnName)
				.queryParam("direction", direction)
				.build()
				.toUri();
	
		ResponseEntity<PaginatedResponse<Staff>> result = restTemplate.exchange(targetUrl, HttpMethod.GET,null,responseType);
		  //http://localhost:8082/filteredemployees?page=1&size=2
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
	public Map<String, Object> getAllPatient(int page,int size,String columnName,String direction){
		log.info("from service all patient details send");
		ParameterizedTypeReference<PaginatedResponse<Patient>> responseType = new ParameterizedTypeReference<PaginatedResponse<Patient>>() { };
		URI targetUrl = UriComponentsBuilder.fromUriString(userServiceURL)
				.path("filteredpatients")
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
	public List<Long> getPatientCount() {
		Long[] count = restTemplate.getForObject(userServiceURL+"/patients/patientcount", Long[].class);
		List<Long> getCount = Arrays.asList(count);
		return getCount;
	}
	@Override
	public List<Long> getStaffCount() {
		return Arrays.asList(restTemplate.getForObject(userServiceURL+"employee/employeecount", Long[].class));
	}
	@Override
	public Optional<UserDto> authenticate(UserDto user) {
		return Optional.of(restTemplate.postForObject(userServiceURL+"auth/verify",user, UserDto.class));
	}
	@Override
	public void editPatientStatus(List<Patient> allPatient) {
			log.info("Inside Admin Service Mehod to edit patient status");
		restTemplate.put(userServiceURL+"patient/editstatus", allPatient);
		
	}
	@Override
	public void editEmployeeStatus(List<Staff> allEmployee) {
		log.info("Inside Admin Service Mehod to edit employee status");
		restTemplate.put(userServiceURL+"employee/editstatus", allEmployee);
		
	}
	


}
