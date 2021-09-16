package com.ct.admin.VO;

import com.ct.admin.utility.Patient;
import com.ct.admin.utility.Role;
import com.ct.admin.utility.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	
	private Patient patient;
	private Role role;
	private Staff staff;
}
