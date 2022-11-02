package com.pb.cucumberdemo.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.pb.cucumberdemo.utils.ConfigUtil;

import cucumber.api.cli.Main;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseFunctions 
{
	public static Properties envConfig = null;
	public static FileInputStream input = null;

	public static RemoteWebDriver driver = null;
	public static boolean isInitialized = false;
	public static boolean isBrowserOpened = false;
	
	public static URL seleniumServerUrl = null;
	
	
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

	//	driver.manage().timeouts().implicitlyWait(Long.parseLong(envConfig.getProperty("Implicit_Wait")), TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}		
	
/*** added for Selenium grid implementation**/
	
@BeforeClass
	static void setupAll() throws MalformedURLException {
	 //   int port = PortProber.findFreePort();
	    int port = 4444;
	    
	    System.out.println("jlkjjjjjjjjjjjjjjjjjj" + port);
	//    WebDriverManager.edgedriver().setup();
	   
	    try {
			Main.main(
			        new String[] { "standalone"});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	    seleniumServerUrl = new URL(
	            "http://localhost:4444"); 
	    
	    System.out.println("jlkjjjjjjjjjjjjjjjjjj" + seleniumServerUrl);
	    
	    
	}
	
	
	
	
	public void openRemoteBrowser() 
	{
		if (!isBrowserOpened) 
		{
			System.out.print("User directory is:"+System.getProperty("user.dir"));
			System.out.print("Value of property:"+envConfig.getProperty("Browser"));
			
			
			 DesiredCapabilities dc = new DesiredCapabilities();
			    dc.setCapability("browserName", "MicrosoftEdge");
			
			
			if (envConfig.getProperty("Browser").equalsIgnoreCase("Chromedddddd")) 
			{
				ChromeOptions chromeOptions = new ChromeOptions();
		//		EdgeOptions options = new EdgeOptions();
				chromeOptions.setCapability("browserVersion", "67");
			//	options.setCapability("browserVersion", "107");
		//		chromeOptions.setCapability("platformName", "Windows XP");
				
				
		//		WebDriver driver;
			//	try {
					
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			//	driver = new RemoteWebDriver(new URL("http://3.138.169.141:4444/"), new EdgeOptions());
					driver = new RemoteWebDriver(seleniumServerUrl, dc);
					   System.out.println("jlkjjjjjjjjjjjjjjjjjj" + seleniumServerUrl);
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				//	driver.get("http://www.google.com");
					//driver.quit();
			//	} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
			//.printStackTrace();
				}
				
				  
			}  
			
		}	

	//	driver.manage().timeouts().implicitlyWait(Long.parseLong(envConfig.getProperty("Implicit_Wait")), TimeUnit.SECONDS);
	//	driver.manage().window().maximize();
			
	
/*** code block for Selenium grid implementation ends here**/
	
	
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
