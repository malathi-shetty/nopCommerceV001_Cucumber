package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobMonth;
	
	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;
	
	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how = How.XPATH, using = "//span[@class='select2-selection select2-selection--multiple']")
	@CacheLookup
	WebElement txtcustomerRoles;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement lstitemAdministrators;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Forum Moderators')]")
	@CacheLookup
	WebElement lstitemForumModerators;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement lstitemRegistered;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement lstitemGuests;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement lstitemVendors;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//div[@id='customers-grid_wrapper']")
	@CacheLookup
	WebElement tblearchResults;
	
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr")
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr/td")
	List<WebElement> tableColumns;
	
	public void setEmail(String email) throws InterruptedException 
	{
		//waithelper.wait();
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(txtEmail));
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname) throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(txtFirstName));
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(txtLastName));
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clickSearch() throws InterruptedException {
		btnSearch.click();
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(btnSearch));
	}
	
	public int getNoOfRows() throws InterruptedException {
		return(tableRows.size());
	}
	
	public int getNoOfColumns() throws InterruptedException {
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email) throws InterruptedException
	{
		boolean flag = false;
		for(int i=1; i<=getNoOfRows(); i++)
		{
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr["+i+"]/td[2]")).getText();
			
			System.out.println(emailid);
			
			if(emailid.equals(email))
			{
				flag = true;
			}
			
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String Name) throws InterruptedException
	{
		boolean flag = false;
		for(int i=1; i<=getNoOfRows(); i++)
		{
			String name = table.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr["+i+"]/td[3]")).getText();
			String names[]=name.split(" ");//split where space occurs - separating first name & lastname stroed in  string array
			
			System.out.println("1: " + names);
			if(names[0].equals("Victoria") && names[1].equals("Terces"))
			{
				flag = true;
			}
			System.out.println("2: " + names);
		}
		return flag;
	}

}
