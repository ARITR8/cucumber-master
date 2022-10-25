package com.pb.cucumberdemo.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.pb.cucumberdemo.utils.ConfigUtil;

public class BaseFunctions 
{
	public static Properties envConfig = null;
	public static FileInputStream input = null;

	public static WebDriver driver = null;
	public static boolean isInitialized = false;
	public static boolean isBrowserOpened = false;
	
	
	/**
	 * @author TGoswami
	 * @date: 15 April 2017
	 * @description: This will initialize property files
	 */
	public static void Initialize() 
	{
		if (!isInitialized) 
		{
			try 
			{					
				ConfigUtil.loadProperty(); 		
				System.out.println("Config Loaded");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @author TGoswami
	 * @date: 15th April 2017
	 * Description: This will Open Browser
	 */
	public void openBrowser() 
	{
		if (!isBrowserOpened) 
		{
			System.out.print("User directory is:"+System.getProperty("user.dir"));
			System.out.print("Value of property:"+envConfig.getProperty("Browser"));
			if (envConfig.getProperty("Browser").equalsIgnoreCase("Chrome")) 
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				
                System.setProperty("webdriver.chrome.driver", Constants.CHROME_EXE);
                driver = new ChromeDriver();
			}  
			
		}	

		driver.manage().timeouts().implicitlyWait(Long.parseLong(envConfig.getProperty("Implicit_Wait")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}		
	
	
	/**
	 * @author TGoswami
	 * @date: 15 April 2017
	 * @description: This will automatically check which locator value you added in object file
	 */
	public static WebElement getElementType(String locator)
	{
		WebElement element;
		
		String pageSource = driver.getPageSource();
		
		if(pageSource.contains("id=\""+locator +"\""))
            element = driver.findElement(By.id(locator));
        else if(pageSource.contains("name=\""+locator +"\""))
            element = driver.findElement(By.name(locator));
        else if(locator.contains("@"))
            element = driver.findElement(By.xpath(locator));
        else
            element = driver.findElement(By.cssSelector(locator));

		return element;
		
	}
	
	/**
	 * @author TGoswami
	 * @date: 15 April 2017
	 * @description: This will find locator value for string from any of object file
	 */
	public String getLocator(String className, String elementName) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException
	{
	
		Class<?> cls = Class.forName("com.pb.cucumberdemo.pageobjs." +className+ "_Objs");
		Method findLocator = cls.getMethod("findLocator", String.class);

		String locator = (String) findLocator.invoke(cls.getDeclaredConstructor().newInstance(), elementName);
	
		return locator;
	}
    
	
	public String getCurrentPage()
	{
		String url = driver.getCurrentUrl();
		String page = "";
				
		if(url.contains("my-account"))
			page = "User";
		else if(url.contains("controller=contact"))
			page = "Contact";
		else
		    page = "Home";
				    
		return page;
		
	}

}
