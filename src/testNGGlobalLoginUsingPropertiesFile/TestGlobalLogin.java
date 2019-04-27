package testNGGlobalLoginUsingPropertiesFile;   //Version 1.0.0    April 15 2015 Hector Hernandez.
// This is part of my practice for learning automation using Selenium Webdriver.
// code is saved in C:\Eclipse\work_space\newproject\

import org.testng.annotations.*;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
			
public class TestGlobalLogin extends GlobalLoginWithExternalDataProvider {
	// GlobalLoginWithExternalDataProvider is the class containing the login() method 
	
    public String baseUrl = null;   //to save this values from the login() method in 
    public String userName = null;  // class GlobalLoginWithExternalDataProvider
    public String password = null; 
    
	public String expected = null;
	public String actual= null;
	   	
		
	  @BeforeTest  //this first test case, will execute before the Test.
	  public void launchBrowser() throws IOException {
		  System.out.println("Launching the browser");
		  
          login();    //call the login() method in class GlobalLoginWithExternalDataProvider
          userName = getUserName(); //get this value to be used below when needed.
          password = getPassword(); //get this value to be used below when needed.
          baseUrl = getURL();       //get this value to be used below when needed.
          System.out.println("*** baseUrl from url: "+baseUrl+" usser "+userName+ " password "+password);	  	  
	  }  
	  
	  @Test (priority = 0)   //below method validate the home page title and login to the application)
	  public void T00_TestAllLinksInHomePage() {

		    List<WebElement> hectorLinkElements = driver.findElements(By.tagName("a")); // Create a list of WebElements containing all the LINK elements in the page.
	        String[] array_linkTexts = new String[hectorLinkElements.size()]; // Create an String array, linkTexts, the size of the LINK elements list.
	        
	        // Create array with the name of all the links in the page.
	        String[] arrayLinkName = new String[] {"Home", "Flights", "Hotels", "Car Rentals", "Cruises", "Destinations",
	        	"Vacations", "SIGN-ON", "REGISTER", "SUPPORT", "CONTACT", "your destination",
	        	"featured vacation destinations", "Register here", "Business Travel @ About.com",
	        	"Salon Travel"};
	        
	        // Create array with the name of all the Pages in the page.
	        String[] arrayPageName = new String[] {"Welcome: Mercury Tours", "Welcome: Mercury Tours", "Under Construction: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Cruises: Mercury Tours", "Under Construction: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Sign-on: Mercury Tours",  "Register: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Under Construction: Mercury Tours",
	        	 "Under Construction: Mercury Tours", "Under Construction: Mercury Tours",
	        	"Register: Mercury Tours",  "404 Not Found",  "Salon.com"};
		        	     
	        int i = 0;
	        //extract the link texts of each link element
	        for (WebElement e : hectorLinkElements) {   
	            array_linkTexts[i] = e.getText(); // assign the text of each link element to the array.
	            i++;
	        }

	        for (int j = 0; j < hectorLinkElements.size(); j++) {
	        	WebElement  ws_element = driver.findElements(By.tagName("a")).get(j); 
	        	//assign the linkElement(j) to the ws_element WebElement.
	        	String linkText = ws_element.getText();   
	        	ws_element.click();  // click on the link save in the element webElement.
	        	String currentPageTitle = driver.getTitle();
	        	String expectedPage = "NONE";
	        	actual =   "ALL Page Title Match the link clicked";
	        	expected = "ALL Page Title Match the link clicked";
	        	boolean found = false;
	        	
	        	for (int n = 0; n < arrayLinkName.length; n++) {
	                if (linkText.equals(arrayLinkName[n])) {
	                	if (currentPageTitle.equals(arrayPageName[n])) {
	            	       found = true; 
	            	       break;
	                	} else {
	                		expectedPage = arrayPageName[n];
	                	}
	                }  
	            }	        	
	        		        		  
	            if (!found) {
	            	actual = "** ONE Page Title DOES NOT Match the link clicked";	            	
	            	System.out.println("******* found ERROR: Not Found one page Title, found = "+found);
	            	System.out.println("currentPageTitle = "+ currentPageTitle + " *** Expected Page Title: " + expectedPage);
	            	break;
	            } 
	            driver.navigate().back();
	        }	  
	      System.out.println(" ---> ACTUAL: " + actual +" ---> EXPECTED: " + expected);
	      driver.get(baseUrl);	  
          Assert.assertEquals(actual, expected);          
	  }
}
