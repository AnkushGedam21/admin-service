package com.ct.admin.utility;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Staff extends User {

	private Integer roleId;

	private Integer empId;

}
