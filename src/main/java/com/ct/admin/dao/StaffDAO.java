package com.ct.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.ct.admin.utility.Staff;

import lombok.Data;

public class StaffDAO {
	private List<Staff> staffs;

	public StaffDAO(Staff staff) {
		if(this.staffs==null) {
			this.staffs = new ArrayList<Staff>();
		}
		this.staffs.add(staff);
	}

	public StaffDAO() {
		staffs = new ArrayList<Staff>();
	}

	public StaffDAO(List<Staff> staffs) {
		super();
		this.staffs = staffs;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	
	
	

}
