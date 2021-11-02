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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


/*
 * @author Ankush Gedam
 * Admin Controller Handle all the admin request handle
 * */



@RestController
//@CrossOrigin("*")
@RequestMapping("admin/")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Logger log;
	
	ResponseEntity<Map<String, Object>> response;
	
	@Operation(summary = "fetch all Patient List", description = "This API is used to fetch all patient list which accept the param"
			+ " page,size,columnName and directon for sortng pagination return the pagnated response")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })

	@GetMapping("patient-list")
	public ResponseEntity<Map<String, Object>> getAllPatient(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "userId") String columnName,
			@RequestParam(defaultValue = "ASC") String direction
			){
		log.info("fetching all patient details");
		
		try {
			response = new ResponseEntity<>(adminService.getAllPatient(page,size,columnName,direction),HttpStatus.OK);
			
			 } catch(Exception e) { 
				response = new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
			 }
		
			 return response;
	}
	@Operation(summary = "fetch all Employee List", description = "This API is used to fetch all Employees Details which accept the param"
			+ " page,size,columnName and directon for sortng pagination return the paginated response")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "staff details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("user-list")
	public ResponseEntity<Map<String, Object>> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "userId") String columnName,
			@RequestParam(defaultValue = "ASC") String direction)
			
	{
		log.info("Fetching all staff details from Admin Controller");
		try {
			response = new ResponseEntity<>(adminService.getAllUsers(page,size,columnName,direction),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch Count of Patient", description = "This API is used to fetch count of the patient "
			+ " It will return total patient, active patient count by their status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient count fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal Server error", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/patients/patientcount")
	public ResponseEntity<Map<String, Object>> patientCount(){
		log.info("Fetching Total patient count");
		try {
			response = new ResponseEntity<>(adminService.getPatientCount(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch all Patient count", description = "This API is used to fetch all staff count "
			+ " which return count of total staff and active staff based on status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/user/usercount")
	public ResponseEntity<Map<String, Object>> staffCount(){
		log.info("Fetching Total Staff count");
		try {
			response = new ResponseEntity<>(adminService.getStaffCount(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/*
	 * It is a admin controller method 
	 * which accept the put request to edit the
	 * patient status
	 */
	@Operation(summary = "Change the Patient Status", description = "This API is used to chage the patient status"
			+ " API accept the list of patient and return response")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient status change successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@PutMapping("patient/editstatus")
	public ResponseEntity<Map<String, Object>> editPatientStatus(@RequestBody List<Patient> allPatient){
		log.info("Inside Admin Controller to edit patient status");
		try {
			
			response = new ResponseEntity<>(adminService.editPatientStatus(allPatient),HttpStatus.OK);
		}

		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/*
	 * This method is responsible for accepting the request to edit the employee
	 * status
	 * and return the Http status
	 */
	@Operation(summary = "Change the Employee Status", description = "This API is used change the employee status"
			+ " API accept the list of employee and return the HTTP status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Staff status has been change successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@PutMapping("employee/editstatus")
	public ResponseEntity<Map<String, Object>> editEmployeeStatus(@RequestBody List<Staff> allEmployee){
		log.info("Inside Admin Controller to edit employee status");
		try{
			
			response = new ResponseEntity<>(adminService.editEmployeeStatus(allEmployee),HttpStatus.OK);
	}

		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch all Patient filtered List", description = "This API is used to fetch all patient filtered list which accept the param"
			+ " page,size, directon and input for filtering sortng pagination")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("filter/patient-list")
	public ResponseEntity<Map<String, Object>> getFilteredPatient(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ASC") String direction,
			@RequestParam String filterValue
			){
		log.info("fetching all patient details");
		
		try {
			response = new ResponseEntity<>(adminService.getFilteredPatient(page,size,direction,filterValue),HttpStatus.OK);
			
			 } catch(Exception e) { 
				 response = new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			 }	
		return response;
	}
	@Operation(summary = "fetch all filtered employees List", description = "This API is used to fetch all filtered employees list which accept the param"
			+ " page,size,directon and input for filtering sortng pagination")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "emplployees details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	
	@GetMapping("filter/user-list")
	public ResponseEntity<Map<String, Object>> getFilterStaffs(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ASC") String direction,
			@RequestParam (defaultValue = "") String filterValue)
			
	{
		log.info("fetching all staff details");
		try {
			response = new ResponseEntity<>(adminService.getFilteredStaff(page,size,direction,filterValue),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch all Patient List", description = "This API is used to fetch all patient list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("patient/allpatients")
	public ResponseEntity<Map<String,Object>> getAllPatient(){
		try {
			response = new ResponseEntity<>(adminService.getAllPatient(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch all active Patient List", description = "This API is used to fetch all active patient list ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Active Patient details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("patient/allactivepatients")
	public ResponseEntity<Map<String,Object>> getAllActivePatient(){
		try {
			response = new ResponseEntity<>(adminService.getAllActivePatient(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch all staff List", description = "This API is used to fetch all staff list ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "staff details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("employees/allemployees")
	public ResponseEntity<Map<String,Object>> getAllEmployee(){
		try {
			response = new ResponseEntity<>(adminService.getAllEmployee(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	@Operation(summary = "fetch all Active Staff List", description = "This API is used to fetch all Active Staff list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Active Staff details fetch successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "SERVER NOT AVALABLE", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("employees/allactiveemployees")
	public ResponseEntity<Map<String,Object>> getAllActiveEmployee(){
		try {
			response = new ResponseEntity<>(adminService.getAllActiveEmployee(),HttpStatus.OK);
		}
		catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


}
