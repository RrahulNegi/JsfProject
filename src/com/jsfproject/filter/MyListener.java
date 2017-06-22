package com.jsfproject.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class MyListener implements ServletContextListener {
@Override
public void contextInitialized(ServletContextEvent arg) {
		System.out.println("contextInitialized....");

		//remove the jsf root logger, avoid duplicated logging
		//try comment out this and see the different on the console
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
	//	System.out.println("contextDestroyed....");

	}

}
