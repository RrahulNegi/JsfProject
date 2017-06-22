package com.jsfproject.daoServices;

import java.util.List;
import java.util.Map;

import com.jsfproject.bean.UserProfileBean;

public interface EditProfileService {

	public Map<String, String> getCountryList(String country);
	
	Map<String, String> getStateList(String str);
	
	public void saveUserProfile(UserProfileBean userBean);
	

	UserProfileBean retriveProfileData(int custId);

}
