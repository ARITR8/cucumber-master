package com.pb.cucumberdemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.pb.cucumberdemo.base.BaseFunctions;
import com.pb.cucumberdemo.base.Constants;

public class ConfigUtil extends BaseFunctions
{
	//to load property file
	public static void loadProperty() throws IOException
	{
//		allConfig = new Properties();
//		input =new FileInputStream(Constants.PROP_File);
//		allConfig.load(input);
		
		envConfig = new Properties();
		input = new FileInputStream(Constants.ENV_FILE);
		envConfig.load(input);	
	

	}
}
