package com.pb.cucumberdemo.stepdefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
		
		plugin = {"pretty", "html:target/cucumber",
				"json:target/cucumber.json" , "junit:target/surefire-reports/test-results.xml"
				 },
		features = {"src/test/resources/features"},
		glue={"com.pb.cucumberdemo.stepdefinitions"},
		monochrome = true,
		tags = {"@tag"},
		dryRun = false,
		strict = true
		)
 
public class CucumberRunner 
{
	
	
}