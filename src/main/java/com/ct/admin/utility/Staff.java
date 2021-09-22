package com.ct.admin.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Staff extends User {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "staff_id")
//	private long staffId;

	private Integer roleId;

	private Integer empId;

//	private String title;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private Date birthDate;

//	private String username;
//	private String password;
//	private boolean deleted;
//	private boolean active;

//	@CreationTimestamp
//	private java.util.Date createdOn;
//
//	@UpdateTimestamp
//	private java.util.Date updatedOn;
}
