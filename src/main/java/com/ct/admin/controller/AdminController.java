package com.ct.admin.controller;

import java.util.Arrays;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct.admin.service.AdminService;
import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.admin.utility.UserDto;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import lombok.extern.slf4j.Slf4j;

/*
 * @author Ankush Gedam
 * Admin Controller Handle all the admin request handle
 * */



@RestController
@CrossOrigin("*")
@RequestMapping("admin")
@Slf4j
public class AdminController {

	@Autowired
	private AdminService adminService;


	@GetMapping("patient-list")
	public ResponseEntity<Map<String, Object>> getAllPatient(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "userId") String columnName,
			@RequestParam(defaultValue = "ASC") String direction
			){
		log.info("fetching all patient details");
		
		try {
			return new ResponseEntity<>(adminService.getAllPatient(page,size,columnName,direction),HttpStatus.OK);
			
			 } catch(Exception e) { return new
			  ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			 }
		
			 
	}
	
	@GetMapping("user-list")
	public ResponseEntity<Map<String, Object>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "userId") String columnName,
			@RequestParam(defaultValue = "ASC") String direction)
			
	{
		log.info("fetching all staff details");
		log.info(columnName);
		log.info(direction);
		try {
			return new ResponseEntity<>(adminService.getAllUsers(page,size,columnName,direction),HttpStatus.OK);
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

	/*
	 * It is a admin controller method 
	 * which accept the put request to edit the
	 * patient status
	 */
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

	/*
	 * This method is responsible for accepting the request to edit the employee
	 * status
	 * and return the Http status
	 */
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
