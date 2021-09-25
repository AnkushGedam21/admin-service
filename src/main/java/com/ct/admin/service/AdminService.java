package com.ct.admin.service;

import java.util.List;
import java.util.Optional;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;
import com.ct.admin.utility.UserDto;

public interface AdminService {
	public Staff[] getAllUsers();
	public Patient[] getAllPatient();
	public Staff getOneUser(long staffId);
	public Patient getOnePatient(long patientId);
	public List<Long> getPatientCount();
	public List<Long> getStaffCount();
	public Optional<UserDto> authenticate(UserDto user);
	public void editPatientStatus(List<Patient> allPatient);
	public void editEmployeeStatus(List<Staff> allEmployee);
}
