package com.ct.admin.service;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Staff;

public interface AdminService {
	public Staff[] getAllUsers();
	public Patient[] getAllPatient();
	public Staff getOneUser(long staffId);
	public Patient getOnePatient(long patientId);
	public void activatePatient(long patientId) ;
	public void deactivatePatient(long patientId);
	public void blockedPatient(long patientId);
	public void activateUser(long staffId);
	public void deactivateUser(long staffId);
	public void blockedStaff(long staffId);

}
