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

public class Staff {


	private long staffId;
	private int role_id;
	private int empId;

	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private java.util.Date birthDate;

	private String username;
	private String password;
	private boolean deleted;
	private String status ;
	private java.util.Date createdOn;
	private java.util.Date updatedOn;
}
