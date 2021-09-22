package com.ct.admin.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Data
public class User {


	private Long userId;
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private java.sql.Date birthDate;
	private String contactNo;
	private String password;
	private Integer attempt;
	private boolean deleted;
	private String status;
	private java.util.Date createdOn;
	private java.util.Date updatedOn;
}
