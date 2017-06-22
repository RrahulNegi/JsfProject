package com.jsfproject.daoServices;

import java.util.List;

import com.jsfproject.bean.EmployeeBean;
import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.controller.EditProfileController;
import com.jsfproject.controller.EmployeeController;
import com.jsfproject.controller.LoginController;

public interface EmployeeService {
	

	public String saveEmployee(EmployeeBean emp);
	
	public List<EmployeeController> getAllEmployee();
	
	
	public String deleteEmoloyee(int empId);
	
	public LoginController validateUser(String username, String password);
	
	public EmployeeBean otpChange(String password, int empId);
	
	public void saveUserProfileData(UserProfileBean upfBean);
	
	public LoginController retriveProfile(Integer custId);
}
