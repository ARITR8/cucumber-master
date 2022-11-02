package com.pb.cucumberdemo.stepdefinitions;

import com.pb.cucumberdemo.base.BaseFunctions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class RawSD extends BaseFunctions
{
	BaseFunctions base = new BaseFunctions();
	
	@Before
	public void beforeScenario()
	{
		base.Initialize();
		base.openBrowser();		
	}
	
	@After
	public void afterScenario()
	{
		//driver.quit();
	}

}
