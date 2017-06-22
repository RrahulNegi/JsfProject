package com.jsfproject.daoServices;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsfproject.bean.UserProfileBean;
import com.jsfproject.dao.EmployeeDao;
import com.jsfproject.properties.MessageProvider;

@Service("editProfileService")
@Transactional
public class EditProfileServiceImpl implements EditProfileService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public Map<String, String> getCountryList(String country) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> countryValue = new LinkedHashMap<String, String>();

		String[] splitStr = country.split("#");
		for (int i = 0; i <= splitStr.length - 1; i++) {

			countryValue.put(splitStr[i].split(":")[1], splitStr[i].split(":")[0]);

		}
		return countryValue;
	}

	@Override
	public Map<String, String> getStateList(String str) {
		// TODO Auto-generated method stub	
		LinkedHashMap<String, String> stateValue = new LinkedHashMap<String, String>();
		if (str != "") {
			String state = new MessageProvider().getValue("state_" + str);
			String[] splitStr = state.split("#");
			for (int i = 0; i <= splitStr.length - 1; i++) {

				stateValue.put(splitStr[i].split(":")[1], splitStr[i].split(":")[0]);

			}
		}
		// logger.info("State is retrived");
		return stateValue;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)

	public void saveUserProfile(UserProfileBean userBean) {
		// TODO Auto-generated method stub
		
		employeeDao.saveUserProfileData(userBean);
	}

	@Override
	public UserProfileBean retriveProfileData(int custId) {
		// TODO Auto-generated method stub
		UserProfileBean userProfile=employeeDao.retriveProfile(custId);
		return userProfile;

	}

}
