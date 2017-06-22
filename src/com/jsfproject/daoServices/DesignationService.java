package com.jsfproject.daoServices;

import java.util.List;

import com.jsfproject.bean.DesignationBean;

public interface DesignationService {

	public String saveDesignation(DesignationBean des);
	
	public List<DesignationBean> showDesignation();
	
	public String deleteDesignation(int desigId);
	
}
