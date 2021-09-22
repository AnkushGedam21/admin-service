package com.ct.admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.admin.utility.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private GeneralService generalService;
	String userServiceURL = "http://USER-SERVICE/";
	public Staff[] getAllUsers(){
		//log.info("from service all user data send");
		return   restTemplate.getForObject(userServiceURL+"employees", Staff[].class);
	}
	public Patient[] getAllPatient(){
		log.info("from service all patient details send");
		return restTemplate.getForObject(userServiceURL+"patients", Patient[].class);
		
	}
	public Staff getOneUser(long userId) {
		return restTemplate.getForObject(userServiceURL+"employees/"+userId, Staff.class);
	}
	public Patient getOnePatient(long patientId) {
		return restTemplate.getForObject(userServiceURL+"patients/"+patientId, Patient.class);
	}
	public void activatePatient(long patientId) {
		restTemplate.put(userServiceURL+"patients/activate/"+patientId, Patient.class);
		Patient patient = restTemplate.getForObject(userServiceURL+"patients/"+patientId, Patient.class);
		String response = generalService.sendMail(patient.getEmail());
		log.info(response);
	}
	@Override
	public void editPatientStatus(long[] allPatientId, String[] allPatientStatus) {
		for(int i =0; i<allPatientId.length;i++) {
			if(allPatientId[i] != 0 && allPatientStatus[i] != "") {
				Patient patient = new Patient();
				patient.setUserId(allPatientId[i]);
				patient.setStatus(allPatientStatus[i]);
				restTemplate.put(userServiceURL+"patient/editpatientstatus", patient);
			}
		}
		
		 
	}
	
	@Override
	public List<Long> getPatientCount() {
		Long[] count = restTemplate.getForObject(userServiceURL+"/patients/patientcount", Long[].class);
		List<Long> getCount = Arrays.asList(count);
		for (Long count1 : getCount) {
			log.info(count.toString());
		}
		return getCount;
	}
	@Override
	public List<Long> getStaffCount() {
		return Arrays.asList(restTemplate.getForObject(userServiceURL+"employee/employeecount", Long[].class));
	}
	@Override
	public Optional<UserDto> authenticate(UserDto user) {
		// TODO Auto-generated method stub
		return Optional.of(restTemplate.postForObject(userServiceURL+"auth/verify",user, UserDto.class));
	}
	


}
