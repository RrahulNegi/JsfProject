package com.jsfproject.dao;

import java.util.List;

import com.jsfproject.bean.DesignationBean;

public interface DesignationDao {

	public void saveDesignation(DesignationBean des) throws Exception;

	public List<DesignationBean> showDesignation() throws Exception;

	public void deleteDesignation(int desigId) throws Exception;
}
