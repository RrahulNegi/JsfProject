package com.jsfproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsfproject.bean.EmployeeBean;
import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.properties.MessageProviderService;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	MessageProviderService messageProviderService;

	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	public void saveEmployee(EmployeeBean emp)throws Exception {
		logger.info("Processing dao save Employee ");
		Session session = sessionFactory.getCurrentSession();

			session.saveOrUpdate(emp);
			logger.info("Employee saved successfully ");

	}

	@SuppressWarnings("unchecked")
	public List<EmployeeBean> getAllEmployee()throws Exception {
		logger.info("Processing dao get employee ");

		List<EmployeeBean> empList = new ArrayList<EmployeeBean>();
		Session session = sessionFactory.getCurrentSession();
		try {

			Criteria cr = session.createCriteria(EmployeeBean.class);
			for (Object obj : cr.list()) {
				empList.add((EmployeeBean) obj);
			}

			logger.info("Retrived all employee ");

		} catch (Exception ex) {
			logger.error("Error in " + ex);

		} 

		return empList;
	}

	public void deleteEmoloyee(int empId)throws Exception {
		logger.info("Processing dao delete employee " + empId);

		Session session = sessionFactory.getCurrentSession();
	
			Query qry = session.createQuery("delete from EmployeeBean where custId=:custId");
			qry.setParameter("custId", empId);
			qry.executeUpdate();
			logger.info("Employee deleted ");

		
	}

	public EmployeeBean validateUser(String username, String password) {
		logger.info("Calling validate user " + username);

		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(EmployeeBean.class);

		cr.add(Restrictions.eq("username", username));
		cr.add(Restrictions.eq("password", password));
		EmployeeBean bean=(EmployeeBean) cr.uniqueResult();
		
		logger.info("Ending validate user ");

		return bean;
	}

	public EmployeeBean otpChange(String password, int empId) {

		logger.info("Processing OTP for user " + empId);

		Session session = sessionFactory.getCurrentSession();
		EmployeeBean emp = new EmployeeBean();
		try {
			emp = (EmployeeBean) session.load(EmployeeBean.class, new Integer(empId));
			emp.setPassword(password);
			emp.setLoginStatus(1);

			logger.info("Processed OTP ");

		} catch (Exception e) {
			logger.error("Error in " + e);

		} 
		return emp;
	}

	public void saveUserProfileData(UserProfileBean upfBean) {
		logger.info("Processing dao save user profile "+upfBean.getEmpId());

		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(upfBean);
		} catch (Exception ex) {
			logger.error("Error in " + ex);

		}		logger.info("Ending save user profile ");

	}

	public UserProfileBean retriveProfile(Integer custId) {
		logger.info("Processing dao retrive profile for " + custId);

		Session session = sessionFactory.openSession();
		UserProfileBean emp = new UserProfileBean();
		UserProfileBean ppf = new UserProfileBean();
		try {
			emp = (UserProfileBean) session.load(UserProfileBean.class, new Integer(custId));
			Criteria cr = session.createCriteria(UserProfileBean.class);
			cr.add(Restrictions.eq("empId", custId));
			ppf = (UserProfileBean) cr.uniqueResult();

			logger.info("Ending dao retrive profile ");

		} catch (Exception e) {
			logger.error("Error in " + e);

		} 
		return ppf;
	}

	@Override
	public EmployeeBean getEmployeeOnId(int id) {
		logger.info("Processing dao get employee ");

		EmployeeBean emp = new EmployeeBean();
		Session session = sessionFactory.getCurrentSession();
		try {

			Criteria cr = session.createCriteria(EmployeeBean.class);
			cr.add(Restrictions.eq("custId", id));
			 emp=(EmployeeBean) cr.uniqueResult();

			logger.info("Retrived all employee ");

		} catch (Exception ex) {
			logger.error("Error in " + ex);

		} 
		return emp;
	}
}