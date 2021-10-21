package com.ct.admin.service;

import java.util.List;
import java.util.Map;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.exceptions.ServerNotAvailableExceptions;

public interface AdminService {
	public Map<String, Object> getAllUsers(int page, int size,String columnName,String direction);
	public Map<String, Object> getAllPatient(int page, int size,String columnName, String direction);
	//@Retryable
	public Map<String, Object> getPatientCount();
	public Map<String, Object> getStaffCount();
	public Map<String, Object> editPatientStatus(List<Patient> allPatient);
	public Map<String, Object> editEmployeeStatus(List<Staff> allEmployee);
}
