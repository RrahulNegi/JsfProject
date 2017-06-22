package com.jsfproject.dao;

import java.util.List;

import com.jsfproject.bean.EmployeeBean;
import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.controller.EmployeeController;

public interface EmployeeDao {

public void saveEmployee(EmployeeBean emp) throws Exception;
	
	public List<EmployeeBean> getAllEmployee() throws Exception;
	
	public EmployeeBean getEmployeeOnId(int id);
	
	public void deleteEmoloyee(int empId) throws Exception;
	
	public EmployeeBean validateUser(String username, String password);
	
	public EmployeeBean otpChange(String password, int empId);
	
	public void saveUserProfileData(UserProfileBean upfBean);
	
	public UserProfileBean retriveProfile(Integer custId);
}
