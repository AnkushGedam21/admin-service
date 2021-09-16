package com.ct.admin.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct.admin.service.AdminService;
import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
@Slf4j
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("patient-list")
	public ResponseEntity<?> getAllPatient(){
		log.info("fetching all patient details");
		try {
		List<Patient> allPatient =Arrays.asList(adminService.getAllPatient());
		return new ResponseEntity<List<Patient>>(allPatient,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("user-list")
	public ResponseEntity<?> getAllUsers(){
		log.info("fetching all staff details");
		try {
			List<Staff> allPatient =Arrays.asList(adminService.getAllUsers());
			return new ResponseEntity<List<Staff>>(allPatient,HttpStatus.OK);
			}
			catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	@GetMapping("patient/{patientId}")
	public ResponseEntity<?> getOnePatient(@PathVariable Long patientId) {
		log.info("Fetcing one Patient details");
		try {
		return new ResponseEntity<Patient>(adminService.getOnePatient(patientId),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	@GetMapping("user/{staffId}")
	public ResponseEntity<?> getOneUser(@PathVariable Long staffId) {
		log.info("Fetching one user details");
		try {
			return new ResponseEntity<Staff>(adminService.getOneUser(staffId),HttpStatus.OK);
			}
			catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	@PutMapping("/patient/activate/{id}")
	String activatePatient(@PathVariable long id) {
		//log.info("Admin COntroller ActivatePatient method called");
		 adminService.activatePatient(id);
		 return "Patient Activated successfully";
	}
	@PutMapping("/patient/deactivate/{id}")
	ResponseEntity<?> deactivatePatient(@PathVariable long id) {
		log.info("Admin COntroller DeactivatePatient method called");
		try {
		adminService.deactivatePatient(id);
		String msg = "Patient Deactivated..!";
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}
		catch(Exception e) {
			String msg = "Patient Status not Edited..!";
			return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	@PutMapping("/patient/blocked/{id}")
	String blockedPatient(@PathVariable long id) {
		adminService.blockedPatient(id);
		return "Patient Blocked..!!";
	}
	@PutMapping("/staff/activate/{id}")
	String activateStaff(@PathVariable long id) {
		adminService.activateUser(id);
		return "User Activated successfully";
	}
	@PutMapping("/staff/deactivate/{id}")
	String deactivateStaff(@PathVariable long id) {
		adminService.deactivateUser(id);
		return "User Activated successfully";
	}
	@PutMapping("/staff/blocked/{id}")
	String blockedStaff(@PathVariable long id) {
		adminService.blockedStaff(id);
		return "User Activated successfully";
	}
}
