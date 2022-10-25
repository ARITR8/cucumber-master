package com.pb.cucumberdemo.pageobjs;

import java.lang.reflect.Field;
import java.util.*;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Objs

{

	// Creation of HashMap
    private static final Map<String, String> homeMap;

	/**
	 * @author TGoswami
	 * @date: 15 April 2017
	 * @description: Declare all page objects here in key value pair
	 */

    static
    {
    	homeMap = new HashMap<String, String>();
    	homeMap.put("searchtextbox", "search_query_top");
    	homeMap.put("searchbutton", "submit_search");
    }
    
	/**
	 * @author TGoswami
	 * @date: 15 April 2017
	 * @description: This will find locator for user string
	 */
	public String findLocator(String elementName)
	{	
		System.out.print("element inside class: "+elementName + "...");
		elementName = elementName.replaceAll("\\s","");
	    
	    if(homeMap.containsKey(elementName))
	    {	
		return(homeMap.get(elementName));
	    }
	    
	 
	 return null;   
	}
    
	



}
