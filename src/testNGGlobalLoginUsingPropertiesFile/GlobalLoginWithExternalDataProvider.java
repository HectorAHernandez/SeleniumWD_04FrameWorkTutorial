package testNGGlobalLoginUsingPropertiesFile;
/* This program shows how to get the userName, password and URL from a properties file, so they
 * can be used globally as the only source for all the javaclass containing all the @test for the
 * application. */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class GlobalLoginWithExternalDataProvider {

	public WebDriver driver = null;  //defines the driver object to be inherit by the calling java
	                                 // class using the extends "GlobalLoginWithExternalDataProvider"
	
	public String userName = null;  //defines the parameters in the .properties file to be used
	public String password = null;  // later as public variable from any calling program/javaclass
	public String url = null;
    		
	@Test
	public void login() throws IOException {
		System.setProperty("webdriver.gecko.driver","C:\\selenium-3.11.0\\geckodriver.exe");
	    System.setProperty("webdriver.chrome.driver", "C:\\selenium-3.11.0\\chromedriver.exe");
	    
		Properties propert = new Properties(); //Properties is a Java class/utility used to define and
		           //instantiate a properties object. 
		//we have to create a file with extension .properties containing all the property/key=value properties/parameters
		//that we are planning to use as global parameters. We have created file "GlobalDataDriven.properties"
		// in the same package "TestNGFiles".
		
		// Now we have to tell to the propert object where our properties file is located.
		FileInputStream fileInputStrm = new FileInputStream("C:\\Eclipse\\work_space\\04FrameWorkTutorial\\src\\testNGGlobalLoginUsingPropertiesFile\\GlobalDataDriven.properties");
		// FileInputStream class is used to point where the properties file is located. the 
		// fileInputStrm object now knows where exactly the file is located.
		propert.load(fileInputStrm); //here the propert object loads the location of the .properties file 
		
		// save Properties or keywords to share values with public or other java class.
		userName = propert.getProperty("userName");
		password = propert.getProperty("password");
		url = propert.getProperty("url");
		      
		
		System.out.println("** the userName from properties file is: "
				+ propert.getProperty("userName"));
		// propert.getProperty("userName") can be used to get the value of the any property in the
		// the .properties file.
		System.out.println("** password from properties file is: "
				+ propert.getProperty("password", "defaultPASSWORD")); //if the key password is not present in the file
		                         //then the default value will be used here		
		System.out.println("** URL from properties file is: "
				+ propert.getProperty("url"));
		// In any test, we can use these code lines to access any keywords in the .properties file.
		
		//Definition of a global routine to use the WebDriver based on a global parameter.
		if (propert.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if (propert.getProperty("browser").contains("chrome")) {
			driver = new ChromeDriver();
		}
		else {
			driver = new InternetExplorerDriver();
		}
		
		//open the url from the properties file:
		driver.manage().window().maximize(); //Maximize the Browser before opening it in driver.get(baseUrl);
		driver.get(propert.getProperty("url"));
	}
	
	public String getURL() {
		return url;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
}
