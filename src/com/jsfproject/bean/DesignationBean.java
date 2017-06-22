package com.jsfproject.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="designation")
public class DesignationBean {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer designationId;
	@Column
	private String designationName;
	
	@Column 
	private String designationValue;
	
	
	public DesignationBean(){}
	
	public DesignationBean(Integer designationId, String designationName, String designationValue) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
		this.designationValue=designationValue;
	}
	
	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getDesignationValue() {
		return designationValue;
	}
	public void setDesignationValue(String designationValue) {
		this.designationValue = designationValue;
	}
	

	

	
	
	

}
