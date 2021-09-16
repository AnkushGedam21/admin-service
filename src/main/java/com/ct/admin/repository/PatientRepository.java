package com.ct.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct.admin.utility.Patient;

@Repository
public interface PatientRepository {

	Patient findByPatientId(long patientId);

	

}
