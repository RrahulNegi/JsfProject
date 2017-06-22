package com.jsfproject.properties;

import java.util.ResourceBundle;

public interface MessageProviderService {
    public ResourceBundle getBundle();
    
    public String getValue(String key);

}
