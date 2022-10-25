package com.pb.cucumberdemo.pageobjs;

import java.lang.reflect.Field;
import java.util.*;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_Objs

{

	// Creation of HashMap
    private static final Map<String, String> contactMap;

	/**
	 * @author TGoswami
	 * @date: 29 June 2018
	 * @description: Declare all page objects here in key value pair
	 */

    static
    {
    	contactMap = new HashMap<String, String>();
    	contactMap.put("emailaddress", "email");
    	contactMap.put("message", "message");
    }
    
	/**
	 * @author TGoswami
	 * @date: 29 June 2018
	 * @description: This will find locator for user string
	 */
	public String findLocator(String elementName)
	{	
		System.out.print("element inside class: "+elementName + "...");
		elementName = elementName.replaceAll("\\s","");
	    
	    if(contactMap.containsKey(elementName))
	    {	
		return(contactMap.get(elementName));
	    }
	    
	 
	 return null;   
	}
    
	



}
