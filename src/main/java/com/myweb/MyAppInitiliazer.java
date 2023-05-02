package com.myweb;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyAppInitiliazer extends AbstractAnnotationConfigDispatcherServletInitializer 
{

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() 
	{
		// Setting the config file
		return new Class[] {MyConfig.class};
	}

	@Override
	protected String[] getServletMappings() 
	{
		// Default servlet URL
		return new String[] {"/"};
	}

}
