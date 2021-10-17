package com.ct.admin.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.admin.utility.UserDto;

public interface AdminService {
	public Map<String, Object>getAllUsers(int page, int size,String columnName,String direction);
	public Map<String, Object> getAllPatient(int page, int size,String columnName, String direction);
	public List<Long> getPatientCount();
	public List<Long> getStaffCount();
	public Optional<UserDto> authenticate(UserDto user);
	public void editPatientStatus(List<Patient> allPatient);
	public void editEmployeeStatus(List<Staff> allEmployee);
}
