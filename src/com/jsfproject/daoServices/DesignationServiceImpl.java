package com.jsfproject.daoServices;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.jsfproject.bean.DesignationBean;
import com.jsfproject.controller.EmployeeController;
import com.jsfproject.dao.DesignationDao;
import com.jsfproject.properties.MessageProviderService;

@Service("masterService")
@Transactional

public class DesignationServiceImpl implements DesignationService {
	@Autowired
	DesignationDao masterDao;
	
	@Autowired
	MessageProviderService messageProviderService;
	private static final Logger logger = Logger.getLogger(DesignationServiceImpl.class);

	@Override
	 @Transactional(propagation=Propagation.REQUIRED)

	public String saveDesignation(DesignationBean des) {
		// TODO Auto-generated method stub
		
		String msg;
		try {
			 masterDao.saveDesignation(des);
			msg = messageProviderService.getValue("data.save");

		} catch (Exception ex) {
			logger.error("Error in " + ex);
			msg = messageProviderService.getValue("err.save");

		} 

		return msg;
		
	}

	@Override
	public List<DesignationBean> showDesignation() {
		// TODO Auto-generated method stub
		List<DesignationBean> list = null;
		try {
			list = masterDao.showDesignation();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			logger.error("Error in " + ex);
		}
		
		return list;
	}

	@Override
	@Transactional

	public String deleteDesignation(int desigId) {
		// TODO Auto-generated method stub
		String msg;
		try {
			 masterDao.deleteDesignation(desigId);
			msg = messageProviderService.getValue("data.delete");

		} catch (Exception ex) {
			logger.error("Error in " + ex);
			msg = messageProviderService.getValue("err.delete");

		} 
		return msg;
		
	}

}
