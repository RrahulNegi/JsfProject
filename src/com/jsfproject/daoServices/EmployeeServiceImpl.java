package com.jsfproject.daoServices;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsfproject.bean.DesignationBean;
import com.jsfproject.bean.EmployeeBean;
import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.controller.EditProfileController;
import com.jsfproject.controller.EmployeeController;
import com.jsfproject.controller.LoginController;
import com.jsfproject.dao.EmployeeDao;
import com.jsfproject.properties.MessageProvider;
import com.jsfproject.properties.MessageProviderService;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	MessageProviderService messageProviderService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String saveEmployee(EmployeeBean emp) {
		// TODO Auto-generated method stub
		EmployeeController empBean = new EmployeeController();
		String msg;
		try {
			employeeDao.saveEmployee(emp);
			msg = messageProviderService.getValue("data.save");
			logger.info("Employee saved successfully ");

		} catch (Exception ex) {
			logger.error("Error in " + ex);
			msg = messageProviderService.getValue("err.save");
		}
		// empBean.setMsg(msg);

		return msg;
	}

	@Override
	public List<EmployeeController> getAllEmployee() {
		// TODO Auto-generated method stub
		List<EmployeeBean> empBean;
		List<EmployeeController> empList = null;
		try {
			empBean = employeeDao.getAllEmployee();
			EmployeeController empManageBean = null;
			empList = new ArrayList<EmployeeController>();
			for (EmployeeBean emp : empBean) {
				empManageBean = new EmployeeController();
				empManageBean.setCustId(emp.getCustId());
				empManageBean.setFirstName(emp.getFirstName());
				empManageBean.setLastName(emp.getLastName());
				empManageBean.setEmail(emp.getEmail());
				empManageBean.setUsername(emp.getUsername());
				empManageBean.setPassword(emp.getPassword());
				empManageBean.setDesignationBean(emp.getDesignationBean());
				empList.add(empManageBean);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in " + e);
		}

		return empList;
	}

	@Override
	@Transactional
	public String deleteEmoloyee(int empId) {
		// TODO Auto-generated method stub
		String msg;
		try {
			employeeDao.deleteEmoloyee(empId);
			msg = messageProviderService.getValue("data.delete");

		} catch (Exception ex) {
			logger.error("Error in " + ex);
			msg = messageProviderService.getValue("err.delete");

		} 
		// EmployeeMangedBean empBean=new
		return msg;

	}

	@Override
	public LoginController validateUser(String username, String password) {
		// TODO Auto-generated method stub

		logger.info("Intializing validateUser in employee service for user: " + username);
		int empId = 0;
		LoginController loginBean = new LoginController();

		if ("admin".equals(username) && "admin".equals(password)) {

			loginBean.setUsername(username);
			loginBean.setCustId(empId);
			loginBean.setSucessPage("adminPage.xhtml");
			loginBean.setLoggedIn(true);

			return loginBean;

		} else {
			EmployeeBean empBeanObj = employeeDao.validateUser(username, password);

			if (empBeanObj != null) {

				logger.info("Login successfull putting username in session");

				loginBean = retriveProfile(empBeanObj.getCustId());

				if (loginBean.getLoginStatus() == 1) {

					logger.info("Login successfull moving to login page");
					loginBean.setSucessPage("loginsucess?faces-redirect=true");
					loginBean.setLoggedIn(true);

					return loginBean;

				} else {
					logger.info("Login successfull moving to OTP page");

					loginBean.setSucessPage("onetimepassword");
					loginBean.setLoggedIn(true);

					return loginBean;
				}
			} else {
				logger.info("Login fail ");
				String msg = messageProviderService.getValue("login.fail");
				loginBean.setMsg(msg);
				loginBean.setSucessPage("index");
				loginBean.setLoggedIn(false);

				return loginBean;
			}
		}

	}

	@Override
	@Transactional
	public EmployeeBean otpChange(String password, int empId) {
		// TODO Auto-generated method stub
		EmployeeBean empBean = employeeDao.otpChange(password, empId);
		return empBean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserProfileData(UserProfileBean upfBean) {
		// TODO Auto-generated method stub
		employeeDao.saveUserProfileData(upfBean);

	}

	@Override
	public LoginController retriveProfile(Integer custId) {
		// TODO Auto-generated method stub
		UserProfileBean upfBean = employeeDao.retriveProfile(custId);
		EditProfileController editProf = new EditProfileController();
		if (upfBean != null) {
			editProf.setCustId(upfBean.getEmpId());
			editProf.setCountry(upfBean.getCountry());
			editProf.setHobbies(upfBean.getHobbies());
			editProf.setHomeAddress(upfBean.getHomeAddress());
			editProf.setMobileNumber(upfBean.getMobileNumber());
		}
		EmployeeBean empBean = employeeDao.getEmployeeOnId(custId);
		EmployeeController empManageBean = new EmployeeController();
		empManageBean.setDesignation(empBean.getDesignationBean().getDesignationName());
		empManageBean.setEmail(empBean.getEmail());
		empManageBean.setFirstName(empBean.getFirstName());
		empManageBean.setLastName(empBean.getLastName());

		LoginController loginBean = new LoginController();
		loginBean.setEditMyProfile(editProf);
		loginBean.setEmployeeMangedBean(empManageBean);
		loginBean.setLoginStatus(empBean.getLoginStatus());
		loginBean.setUsername(empBean.getUsername());
		loginBean.setCustId(custId);
		return loginBean;
	}

}
