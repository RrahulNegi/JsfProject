package com.jsfproject.bean;

import java.io.Serializable;

public class StudentBean implements Serializable {

	private String name;

	public StudentBean(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
