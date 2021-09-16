package com.ct.admin.service;

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
		return   restTemplate.getForObject(userServiceURL+"staffs", Staff[].class);
	}
	public Patient[] getAllPatient(){
		log.info("from service all patient details send");
		return restTemplate.getForObject(userServiceURL+"patients", Patient[].class);
		
	}
	public Staff getOneUser(long staffId) {
		return restTemplate.getForObject(userServiceURL+"staffs/"+staffId, Staff.class);
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
	public void deactivatePatient(long patientId) {
		restTemplate.put(userServiceURL+"patients/deactivate/"+patientId, Patient.class);
		Patient patient = restTemplate.getForObject(userServiceURL+"patients/"+patientId, Patient.class);
		String response = generalService.sendMail(patient.getEmail());
		log.info(response);
	}
	public void blockedPatient(long patientId) {
		restTemplate.put(userServiceURL+"patients/blocked/"+patientId, Patient.class);
		Patient patient = restTemplate.getForObject(userServiceURL+"patients/"+patientId, Patient.class);
		String response = generalService.sendMail(patient.getEmail());
		log.info(response);	
	}
	public void activateUser(long staffId) {
		log.info("Admin Service ActivatePatient method called");
		restTemplate.put(userServiceURL+"staffs/activate/"+staffId, Staff.class);
		Staff staff = restTemplate.getForObject(userServiceURL+"staffs/"+staffId, Staff.class);
		String response = generalService.sendMail(staff.getEmail());
		log.info(response);
	}
	public void deactivateUser(long staffId) {
		restTemplate.put(userServiceURL+"staffs/deactivate/"+staffId, Staff.class);
		Staff staff = restTemplate.getForObject(userServiceURL+"staffs/"+staffId, Staff.class);
		String response = generalService.sendMail(staff.getEmail());
		log.info(response);
		}
	public void blockedStaff(long staffId) {
		restTemplate.put(userServiceURL+"staffs/blocked/"+staffId, Staff.class);
		Staff staff = restTemplate.getForObject(userServiceURL+"staffs/"+staffId, Staff.class);
		String response = generalService.sendMail(staff.getEmail());
		log.info(response);
		}



}
