package com.ct.admin.controller;

import java.util.Arrays;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct.admin.service.AdminService;
import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.admin.utility.UserDto;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
@Slf4j
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/verify")
	public ResponseEntity<?> authenticate(@RequestBody UserDto user) {
		log.info("INSIDE Authenticate");

		Optional<UserDto> optional = adminService.authenticate(user);
		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Problem.create().withTitle("Invalid Login").withDetail("Email or Passoword is Mismatch"));
		}

		UserDto authenticatedUser = optional.get();
		return ResponseEntity.created(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(AdminController.class).authenticate(authenticatedUser)).toUri())
				.body(authenticatedUser);
	}
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
			List<Staff> allEmployee =Arrays.asList(adminService.getAllUsers());
			return new ResponseEntity<List<Staff>>(allEmployee,HttpStatus.OK);
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
	@GetMapping("user/{userId}")
	public ResponseEntity<?> getOneUser(@PathVariable Long userId) {
		log.info("Fetching one user details");
		try {
			return new ResponseEntity<Staff>(adminService.getOneUser(userId),HttpStatus.OK);
			}
			catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	@GetMapping("/patients/patientcount")
	ResponseEntity<?> patientCount(){
		try {
			return new ResponseEntity<List<Long>>(adminService.getPatientCount(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/user/usercount")
	ResponseEntity<?> staffCount(){
		try {
			return new ResponseEntity<List<Long>>(adminService.getStaffCount(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("patient/editstatus")
	public ResponseEntity<?> editPatientStatus(@RequestBody List<Patient> allPatient){
		log.info("Inside Admin Controller to edit patient status");
		try {
			adminService.editPatientStatus(allPatient);
			return new ResponseEntity<Patient>(HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("employee/editstatus")
	public ResponseEntity<?> editEmployeeStatus(@RequestBody List<Staff> allEmployee){
		log.info("Inside Admin Controller to edit employee status");
		try {
			adminService.editEmployeeStatus(allEmployee);
			return new ResponseEntity<Patient>(HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
