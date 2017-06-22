package com.jsfproject.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.springframework.stereotype.Service;

@Service("messageProviderService")
public class MessageProvider implements MessageProviderService {
	 private ResourceBundle bundle;
	 
	    public ResourceBundle getBundle() {
	        if (bundle == null) {
	            FacesContext context = FacesContext.getCurrentInstance();
	            bundle = context.getApplication().getResourceBundle(context, "msgs");
	        }
	        return bundle;
	    }
	 
	    public String getValue(String key) {
	 
	        String result = null;
	        try {
	            result = getBundle().getString(key);
	        } catch (MissingResourceException e) {
	            result = "???" + key + "??? not found";
	        }
	        return result;
	    }
	 
	
}
