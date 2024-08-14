package stepDefiniiton_1;






import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;

/*import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
*/

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.*;
import net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	@Before
		public void setup() throws IOException
		{
		//Reading configured property class
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		 
		
		logger = LogManager.getLogger("nopCommerce"); // added logger
	
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
		configProp.getProperty("chromepath");
		driver = new ChromeDriver();
		}else if(br.equals("firefox"))
		{
		configProp.getProperty("firefoxpath");
		driver = new FirefoxDriver();
		}else if(br.equals("ie"))
		{
		configProp.getProperty("iepath");
		driver = new FirefoxDriver();
		}
		
		
		
		
		
		
		logger.info("*****Launching Chrome Browser********");
	    // Example usage of the logger
	    logger.info("Logging initialized programmatically");

	    logger.debug("Debug message");
	    logger.error("Error message");
		}
	
	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
		
	//	logger = LogManager.getLogger("nopCommerce"); // added logger
	/*	
	////******* No need to initialize Log4j programmatically if using properties file
        // Log4j will automatically load configuration from log4j2.properties
       // *********/
	//***	 // Load configuration from log4j.properties file
   /*     ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setConfigurationName("log4j.properties");
		  
        //PropertyConfigurator.configure("Log4j.properties");
        
        // Configure appenders and loggers programmatically if needed
        // Typically, for properties-based configuration, you don't need to add them again

        // Build the configuration
        Configuration config = builder.build();

        // Initialize Log4j with the configured configuration
        LoggerContext context = Configurator.initialize(config).getContext();
//***********
 /*       // Example usage of the logger
        logger.info("Logging initialized programmatically");

        logger.debug("Debug message");
        logger.error("Error message");

	logger.info("*****Launching Chrome Browser********");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		*/
		
		lp = new LoginPage(driver);
	    
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		
		logger.info("*****Launching URL********");
	   driver.get(url);
	   driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String pwd) throws InterruptedException {
		
		logger.info("*****Providing login details********");
		
	    lp.setUserName(email);
	    lp.setPassword(pwd);
	    Thread.sleep(3000);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		logger.info("*****Started login process********");
	  lp.clickLogin();
	  Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
		logger.info("*****Login was unsuccess********");
	
		if(driver.getPageSource().contains("Login was unsuccessful.")) 
		{
			driver.close();
			Assert.assertTrue(false);
		}else {
			Assert.assertEquals(title, driver.getTitle());
			 Thread.sleep(3000);
		}
	    
	}

	@When("User click on Logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		logger.info("*****Click on Logout********");
	   lp.clickLogout();
	   Thread.sleep(3000);
	}

	@Then("Close Browser")
	public void close_browser() {
		logger.info("*****Closing Chrome Browser********");
	   driver.quit();
	}
	
	
	//Customers feature Step definitions.............................
	

	@Then("User can view Dashboard")
	public void user_can_view_dashboard() 
	{
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException 
	{
		Thread.sleep(3000);
		addCust.clickonCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException 
	{
		Thread.sleep(3000);
		addCust.clickonCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException 
	{
		Thread.sleep(3000);
		addCust.clickonAddnew();
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() throws InterruptedException 
	{
		Thread.sleep(3000);
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
		
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException 
	{
		logger.info("*****Adding & Providing new customer details********");
		Thread.sleep(3000);
		String email=randomstring()+"@gmail.com";
		addCust.setEmail(email);
		
		addCust.setPassword("test123");
		Thread.sleep(3000);

	
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Female");
		addCust.setFirstName("Angela");
		addCust.setLastName("John");
		addCust.setDob("7/05/1985"); // Format: D/MM/YYYY
		addCust.setCompanyName("Busy");
		addCust.setAdminContent("Testing.....");
	//	addCust.setCustomerRoles("Guests"); // Registered - default
		// The customer cannot be in both "Guests" and "Registered" customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException 
	{
		logger.info("*****Saving Customer details********");
		addCust.clickOnSave();
		Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) 
	{
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
		
	  
	}
	
	
	//Steps for searching a Customer by using Email ID
	
	@When("Enter customer Email")
	public void enter_customer_email() throws InterruptedException {
		logger.info("*****Searching customer by email id********");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
		
	}

	@When("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should find Email in the Search table")
	public void user_found_email_in_the_search_table() throws InterruptedException {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	

	// Steps for searching customer by using FirstName and LastName
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() throws InterruptedException {
		logger.info("*****Searching customer by using FirstName and LastName********");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() throws InterruptedException {
		searchCust.setLastName("Terces");
	}

	@Then("User should find Name in the Search table")
	public void user_should_find_name_in_the_search_table() throws InterruptedException {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}
}
