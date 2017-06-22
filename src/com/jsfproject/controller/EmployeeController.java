package com.jsfproject.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsfproject.bean.DesignationBean;
import com.jsfproject.bean.EmployeeBean;
import com.jsfproject.daoServices.EmployeeService;
import com.jsfproject.daoServices.DesignationService;
import com.jsfproject.properties.MessageProvider;
import com.jsfproject.properties.MessageProviderService;

@Component("employee")
// @ManagedBean(name = "employee")
@SessionScoped
public class EmployeeController implements java.io.Serializable {

	
	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;

	@Autowired
	DesignationService masterService;
	
	@Autowired
	MessageProviderService messageProviderService;
	
	
	private static final long serialVersionUID = 1L;
	private Integer custId;
	private String firstName;
	private String lastName;
	private String email;
	private String msg;
	private String deleteMsg;
	private String username;
	private String password;
	private String confPassword;
	private String designation;
	private Integer designationId;
	private DesignationBean designationBean;

	
	List<EmployeeController> empList = new ArrayList<EmployeeController>();
	List<DesignationBean> desigList = new ArrayList<DesignationBean>();
	String designationVal;
	Map<String, Integer> designationList = new LinkedHashMap<String, Integer>();

	public EmployeeController() {
	}

	public EmployeeController(Integer custId, String firstName, String lastName, String email, String username,
			String password, String designationVal) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.custId = custId;
		this.username = username;
		this.password = password;
		this.designationVal = designationVal;

	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Integer getCustId() {
		return this.custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDeleteMsg() {
		return deleteMsg;
	}

	public void setDeleteMsg(String deleteMsg) {
		this.deleteMsg = deleteMsg;
	}

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

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}
	

	/**
	 * @return the designationBean
	 */
	public DesignationBean getDesignationBean() {
		return designationBean;
	}

	/**
	 * @param designationBean the designationBean to set
	 */
	public void setDesignationBean(DesignationBean designationBean) {
		this.designationBean = designationBean;
	}

	
	public String showEmployeeData() {
		logger.info("Initializing the employee managment values ");
		// empList=dao.getAllEmployee();
		empList = getAllCustomers();

		desigList = masterService.showDesignation();

		for (DesignationBean dBean : desigList) {
			designationList.put(dBean.getDesignationName(), dBean.getDesignationId());
		}
		logger.info("Initializing is completed");
		return "employeeForm.xhtml";

	}

	
	public void saveEmployee() {

		logger.info("Admin saving employee");

		if (custId == 0) {
			custId = null;
		}
		EmployeeBean empBean = new EmployeeBean(custId, firstName, lastName, email, username, password);
		DesignationBean desigBean = new DesignationBean();
		desigBean.setDesignationId(designationId);
		empBean.setDesignationBean(desigBean);

		String message = employeeService.saveEmployee(empBean);

		this.msg = message;
	//	this.empList=getAllCustomers();
		reset();
		
		logger.info("Employee Data is saved");
	}

	public List<EmployeeController> getAllCustomers() {

			empList = employeeService.getAllEmployee();

		
		return empList;
	}

	public void editEmployee(EmployeeController emgBean) {

		logger.info("Admin editing employee "+emgBean.getCustId());
		this.custId = emgBean.getCustId();
		this.firstName = emgBean.getFirstName();
		this.lastName = emgBean.getLastName();
		this.email = emgBean.getEmail();
		this.username = emgBean.getUsername();
		this.password = emgBean.getPassword();

		this.designationId = emgBean.getDesignationBean().getDesignationId();
		this.msg = "";

		logger.info("Admin updated employee");

	}

	public void deleteEmployee(EmployeeController empBean) {
		Integer empId = empBean.getCustId();
		logger.info("Admin deleting  employee "+ empId);
		String msg = employeeService.deleteEmoloyee(empId);
		this.msg =  msg;
		this.empList=getAllCustomers();

		logger.info("Admin deleted  employee");
	}

	public String otpChange() {
		logger.info("Processing OTP call for " + custId);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		Map<String, String> params = externalContext.getRequestParameterMap();

		Integer custId = Integer.valueOf(params.get("custId"));

		logger.debug("Retriving otp for emp: {} " );

		employeeService.otpChange(password, custId);
		this.msg =messageProviderService.getValue("pwd.changed"); 

		logger.info("OTP call is processed");

		return "index";

	}

	public void reset() {
		empList = getAllCustomers();
		this.custId = null;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.username = "";
		this.password = "";
		this.designationId = null;

	}

	/**
	 * @return the designationVal
	 */
	public String getDesignationVal() {
		return designationVal;
	}

	/**
	 * @param designationVal
	 *            the designationVal to set
	 */
	public void setDesignationVal(String designationVal) {
		this.designationVal = designationVal;
	}

	/**
	 * @return the designationList
	 */
	public Map<String, Integer> getDesignationList() {
		return designationList;
	}

	/**
	 * @param designationList
	 *            the designationList to set
	 */
	public void setDesignationList(Map<String, Integer> designationList) {
		this.designationList = designationList;
	}

}
