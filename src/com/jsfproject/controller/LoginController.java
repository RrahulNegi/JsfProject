package com.jsfproject.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsfproject.bean.EmployeeBean;
import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.dao.EmployeeDaoImpl;
import com.jsfproject.daoServices.EmployeeService;
import com.jsfproject.filter.Util;

@Component("loginBean")
// @ManagedBean(name = "loginBean")
@SessionScoped
public class LoginController implements Serializable {
	/**
	 * 
	 */

	@Autowired
	EmployeeService employeeService;

	private static final Logger logger = Logger.getLogger(LoginController.class);

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private int custId;
	private String msg;
	private boolean loggedIn;

	private String sucessPage;

	private String confPassword;
	
	private int loginStatus;

	EmployeeController employeeMangedBean;
	@Autowired
	EditProfileController editMyProfile;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	EmployeeBean employeeBean;

	public EmployeeBean getEmployeeBean() {
		return employeeBean;
	}

	public void setEmloyeeBean(EmployeeBean employeeBean) {
		this.employeeBean = employeeBean;
	}

	public String getSucessPage() {
		return sucessPage;
	}

	public void setSucessPage(String sucessPage) {
		this.sucessPage = sucessPage;
	}

	public EmployeeController getEmployeeMangedBean() {
		return employeeMangedBean;
	}

	public void setEmployeeMangedBean(EmployeeController employeeMangedBean) {
		this.employeeMangedBean = employeeMangedBean;
	}

	/**
	 * @return the editMyProfile
	 */
	public EditProfileController getEditMyProfile() {
		return editMyProfile;
	}

	/**
	 * @param editMyProfile
	 *            the editMyProfile to set
	 */
	public void setEditMyProfile(EditProfileController editMyProfile) {
		this.editMyProfile = editMyProfile;
	}
	
	

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	EmployeeDaoImpl dao = new EmployeeDaoImpl();


	public String validateUser() {
		logger.info("Validating user " + this.username);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		String page = "";
		LoginController loginBean = employeeService.validateUser(this.username, this.password);
		page = loginBean.getSucessPage();
		this.msg=loginBean.getMsg();
		this.custId=loginBean.getCustId();
		this.employeeMangedBean=loginBean.getEmployeeMangedBean();
		this.editMyProfile=loginBean.getEditMyProfile();
		if (loginBean.isLoggedIn()) {
			session.setAttribute("username", loginBean.getUsername());

			session.setAttribute("custId", loginBean.getCustId());
			
			
		}
		return page;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.msg = "";
		logger.info("User is logout");
		return "/index.xhtml";
	}
	
	public void retriveUserData(){
		/*UserProfileBean upfBean = employeeService.retriveProfile(this.custId);
		EditMyProfile empBean = new EditMyProfile();
		empBean.setHomeAddress(upfBean.getHomeAddress());
		empBean.setMobileNumber(upfBean.getMobileNumber());
		this.editMyProfile = empBean;*/

	}

}