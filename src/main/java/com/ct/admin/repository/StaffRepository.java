package com.ct.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct.admin.utility.Staff;

@Repository
public interface StaffRepository {

	Staff findByStaffId(long staffId);


}
