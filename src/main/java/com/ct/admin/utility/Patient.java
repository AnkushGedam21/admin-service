package com.ct.admin.utility;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Patient {

	private long patientId;
	
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private java.util.Date birthDate;
	private long contactNo;
	
	private String race;
	private String ethnicity;
	private String languages;
	private String address;
	
	private String username;
	private String password;
	private boolean deleted;
	private String status;

	
	private java.util.Date createdOn;
	private java.util.Date updatedOn;
}