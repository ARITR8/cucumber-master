package com.pb.cucumberdemo.pageobjs;

import java.util.HashMap;
import java.util.Map;

public class User_Objs 
{
	// Creation of HashMap
    private static final Map<String, String> userMap;

	
    static
    {
    	userMap = new HashMap<String, String>();
    	userMap.put("emailaddress", "email");
    	userMap.put("password", "passwd");
    	userMap.put("signin", "SubmitLogin");
    	userMap.put("createanaccountbutton", "SubmitCreate");
    	userMap.put("registrationemailtextbox", "email_create");
    	userMap.put("registerbutton", "submitAccount");
    }

	public String findLocator(String elementName)
	{	
		System.out.print("element inside class: "+elementName + "...");
		elementName = elementName.replaceAll("\\s","");
	    
	    if(userMap.containsKey(elementName))
	    {	
		return(userMap.get(elementName));
	    }
	    
	 
	 return null;   
	}
    

}
