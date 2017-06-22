package com.jsfproject.controller;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsfproject.bean.DesignationBean;
import com.jsfproject.dao.EmployeeDaoImpl;
import com.jsfproject.dao.DesignationDaoImpl;
import com.jsfproject.daoServices.DesignationService;

@Component("designationBean")
// @ManagedBean(name="designationBean",eager=true)
@SessionScoped
public class DesignationController {

	@Autowired
	DesignationService masterService;

	private static final Logger logger = Logger.getLogger(DesignationController.class);

	private Integer designationId;
	private String designationName;
	private String designationValue;
	private String msg;

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	List<DesignationBean> desigList = new ArrayList<DesignationBean>();

	public String showDesignationData() {
		logger.info("Designation init method processing");
		List<DesignationBean> list = masterService.showDesignation();
		this.desigList = list;
		logger.info("Designation init method processed");
		return "designationPage?faces-redirect=true";

	}

	public List<DesignationBean> getDesigList() {
		return desigList;
	}

	public void setDesigList(List<DesignationBean> desigList) {
		this.desigList = desigList;
	}

	public DesignationController() {
	}

	public DesignationController(Integer designationId, String designationName, String designationValue) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
		this.designationValue = designationValue;
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

	public void saveDesignation() {

		logger.info("admin is saving designation ");
		DesignationBean desigBean = new DesignationBean(designationId, designationName, designationValue);
		String msg = masterService.saveDesignation(desigBean);
		this.msg = msg;
		// getShowDesignation();
		reset();
		logger.info("Designation is saved ");

	}

	public List<DesignationBean> getShowDesignation() {

		desigList = masterService.showDesignation();

		return desigList;
	}

	public void editDesignation(DesignationBean bean) {

		logger.info("admin is editing designation ");
		this.designationId = bean.getDesignationId();
		this.designationName = bean.getDesignationName();
		this.designationValue = bean.getDesignationValue();
		logger.info("Designation is updated ");

	}

	public void deleteDesignation(DesignationBean bean) {

		Integer id = bean.getDesignationId();
		logger.info("admin is deleting designation " + id);

		String message = masterService.deleteDesignation(id);
		this.msg = "";
		this.msg = message;

		getShowDesignation();
		reset();
		logger.info("Designation id= " + id + " deleted");

	}

	public void reset() {
		desigList = getShowDesignation();
		this.designationName = "";
		this.designationValue = "";
		this.designationId = null;

	}

}
