package com.jsfproject.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeBean {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer custId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	
	
	@Column
	private int loginStatus;


	@OneToOne
	@JoinColumn(name="designationId")
	private DesignationBean designationBean;
	public EmployeeBean(){
		
	}
	public EmployeeBean(Integer custId, String firstName, String lastName, String email, String username, String password) {
		super();
		this.custId = custId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username=username;
		this.password=password;
		
		
	}

	public DesignationBean getDesignationBean() {
		return designationBean;
	}
	public void setDesignationBean(DesignationBean designationBean) {
		this.designationBean = designationBean;
	}
	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}


}
