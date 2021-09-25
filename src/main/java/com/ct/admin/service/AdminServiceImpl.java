package com.ct.admin.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
