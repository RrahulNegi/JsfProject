package com.jsfproject.controller;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.dao.EmployeeDaoImpl;
import com.jsfproject.daoServices.EditProfileService;
import com.jsfproject.daoServices.EmployeeService;
import com.jsfproject.filter.Util;
import com.jsfproject.properties.MessageProvider;

@Component("editProfile")
// @ManagedBean(name = "editProfile")
@SessionScoped
public class EditProfileController {
	private static final Logger logger = Logger.getLogger(EditProfileController.class);

	private String country;
	Map<String, String> countryValue;
	private String state;
	private Map<String, String> stateValue;
	private String homeAddress;
	private String mobileNumber;
	private String hobbies;
	private Integer custId;
	private Integer userId;
	
	
@ManagedProperty(value="#{loginBean}")
	LoginController loginBean;

	@Autowired
	EditProfileService editProfileService;
	
	@Autowired
	EmployeeService employeeService;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<String, String> getCountryValue() {
		return countryValue;
	}

	public void setCountryValue(Map<String, String> countryValue) {
		this.countryValue = countryValue;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, String> getStateValue() {
		return stateValue;
	}

	public void setStateValue(Map<String, String> stateValue) {
		this.stateValue = stateValue;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public LoginController getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginController loginBean) {
		this.loginBean = loginBean;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/*
	 * public UploadedFile getPicture() { return picture; }
	 * 
	 * public void setPicture(UploadedFile picture) { this.picture = picture; }
	 */
	// @PostConstruct
	public String showEmpProfileData() {
		logger.info("Initializing the values of profile");

		HttpSession session = Util.getSession();

		countryValue = getCountryList();
		// stateValue=getStateList();
		custId = (Integer) session.getAttribute("custId");
		//this.userId=custId;
		retriveProfileData();
		logger.info("Initializing is completed");

		return "editProfile.xhtml";

	}

	EmployeeDaoImpl dao = new EmployeeDaoImpl();

	public String getMessage() {
		String msg = new MessageProvider().getValue("message");
		getCountryList();
		return MessageFormat.format(msg, "SomeValue");
	}

	public Map<String, String> getCountryList() {

		logger.info("Retriving country ");
		String country = new MessageProvider().getValue("country");

		countryValue = editProfileService.getCountryList(country);
		// String country = new MessageProvider().getValue("country");
		return countryValue;

	}

	public Map<String, String> getStateList(String str) {

		logger.info("Retriving state");
		logger.debug("Processing state data : {} " + str);

		stateValue = editProfileService.getStateList(str);
		logger.info("State is retrived");
		return stateValue;

	}

	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		// assign new value to localeCode
		state = e.getNewValue().toString();
		getStateList(state);

	}

	public String saveUserProfile() {

		logger.info("Saving User profile");
		if (userId == 0) {
			userId = null;
		}
		UserProfileBean upfBean = new UserProfileBean();
		upfBean.setUserId(userId);
		upfBean.setCountry(this.country);
		upfBean.setState(this.state);
		upfBean.setHobbies(this.hobbies);
		upfBean.setMobileNumber(this.mobileNumber);
		upfBean.setEmpId(this.custId);
		upfBean.setHomeAddress(this.homeAddress);

		editProfileService.saveUserProfile(upfBean);
		retriveUpdatedUserData(this.custId);
		logger.info("User profile is saved");
		// upfBean=editProfileService.retriveProfileData(custId);

		return "loginsucess?faces-redirect=true";

	}

	public void reset() {

		this.custId = null;
		this.country = "";
		this.state = "";
		this.hobbies = "";
		this.mobileNumber = "";
		this.homeAddress = "";

	}

	public void retriveProfileData() {

		logger.info( "Retriving profile data : {} " + custId);
		UserProfileBean bean = editProfileService.retriveProfileData(custId);

		// EditMyProfile edtProfile=new EditMyProfile();
		if (bean != null) {
			this.country = bean.getCountry();
			this.state = bean.getState();
			this.hobbies = bean.getHobbies();
			this.homeAddress = bean.getHomeAddress();
			this.userId = bean.getUserId();
			this.mobileNumber = bean.getMobileNumber();
			this.stateValue = getStateList(country);
		}
		logger.debug("Profile data is retrived");

	}
	
	public void retriveUpdatedUserData(int custId){
		logger.info("Retriving profile data : {} " + custId);

		this.loginBean=employeeService.retriveProfile(custId);
	}

}