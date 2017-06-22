package com.jsfproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsfproject.bean.DesignationBean;
import com.jsfproject.config.HibernateUtil;

import com.jsfproject.properties.MessageProviderService;

@Repository("masterDao")
public class DesignationDaoImpl implements DesignationDao {
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	MessageProviderService messageProviderService;

	private static final Logger logger = Logger.getLogger(DesignationDaoImpl.class);

	public void saveDesignation(DesignationBean des)throws Exception {
		logger.info("Processing dao save designation : "+des.getDesignationName());


		Session session = sessionFactory.getCurrentSession();
		
			session.saveOrUpdate(des);
	}

	@SuppressWarnings("unchecked")
	public List<DesignationBean> showDesignation()throws Exception {
		logger.info("Processing dao show designation");

		Session session = sessionFactory.getCurrentSession();
		List<DesignationBean> list = new ArrayList<DesignationBean>();
		Query qry = session.createQuery("from DesignationBean");

		list = qry.list();

		logger.info("Ending dao show designation ");

		return list;

	}

	public void deleteDesignation(int desigId)throws Exception {
		logger.info("Processing dao delete designation of id " + desigId);

		String msg = "";

		Session session = sessionFactory.getCurrentSession();
		
			Query q = session.createQuery("delete from DesignationBean where designationId=:desigId");
			q.setParameter("desigId", desigId);
			q.executeUpdate();
			logger.info("Ending dao delete designation ");

	}

}
