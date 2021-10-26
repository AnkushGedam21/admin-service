package com.ct.admin.controller;


import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct.admin.service.AdminService;
import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;


/*
 * @author Ankush Gedam
 * Admin Controller Handle all the admin request handle
 * */



@RestController
@CrossOrigin("*")
@RequestMapping("admin/")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Logger log;
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
		try {
			return new ResponseEntity<>(adminService.getAllUsers(page,size,columnName,direction),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/patients/patientcount")
	public ResponseEntity<Map<String, Object>> patientCount(){
		log.info("Fetching Total patient count");
		try {
			return new ResponseEntity<>(adminService.getPatientCount(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/user/usercount")
	public ResponseEntity<Map<String, Object>> staffCount(){
		log.info("Fetching Total Staff count");
		try {
			return new ResponseEntity<>(adminService.getStaffCount(),HttpStatus.OK);
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
	public ResponseEntity<Map<String, Object>> editPatientStatus(@RequestBody List<Patient> allPatient){
		log.info("Inside Admin Controller to edit patient status");
		try {
			
			return new ResponseEntity<>(adminService.editPatientStatus(allPatient),HttpStatus.OK);
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
	public ResponseEntity<Map<String, Object>> editEmployeeStatus(@RequestBody List<Staff> allEmployee){
		log.info("Inside Admin Controller to edit employee status");
		log.info(allEmployee.toString());
		try{
			
			return new ResponseEntity<>(adminService.editEmployeeStatus(allEmployee),HttpStatus.OK);
	}

		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("filter/patient-list")
	public ResponseEntity<Map<String, Object>> getFilteredPatient(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ASC") String direction,
			@RequestParam String filterValue
			){
		log.info("fetching all patient details");
		
		try {
			return new ResponseEntity<>(adminService.getFilteredPatient(page,size,direction,filterValue),HttpStatus.OK);
			
			 } catch(Exception e) { return new
			  ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			 }	 
	}
	
	@GetMapping("filter/user-list")
	public ResponseEntity<Map<String, Object>> getFilterStaffs(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ASC") String direction,
			@RequestParam (defaultValue = "") String filterValue)
			
	{
		log.info("fetching all staff details");
		try {
			return new ResponseEntity<>(adminService.getFilteredStaff(page,size,direction,filterValue),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("patient/allpatients")
	public ResponseEntity<Map<String,Object>> getAllPatient(){
		//try {
			return new ResponseEntity<>(adminService.getAllPatient(),HttpStatus.OK);
//		}
//		catch(Exception e) {
//			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	@GetMapping("patient/allactivepatients")
	public ResponseEntity<Map<String,Object>> getAllActivePatient(){
		try {
			return new ResponseEntity<>(adminService.getAllActivePatient(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("employees/allemployees")
	public ResponseEntity<Map<String,Object>> getAllEmployee(){
		try {
			return new ResponseEntity<>(adminService.getAllEmployee(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("employees/allactiveemployees")
	public ResponseEntity<Map<String,Object>> getAllActiveEmployee(){
		try {
			return new ResponseEntity<>(adminService.getAllActiveEmployee(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
