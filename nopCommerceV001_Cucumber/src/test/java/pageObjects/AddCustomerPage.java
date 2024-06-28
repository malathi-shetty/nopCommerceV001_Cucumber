package pageObjects;



import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCustomerPage {
	
	public WebDriver ldriver;
	
		public AddCustomerPage(WebDriver rdriver) 
		{
			ldriver = rdriver;
			PageFactory.initElements(ldriver, this);
		}
		
		By lnkCustomers_menu=By.xpath("(//a[@href='#']/following::p[normalize-space(text())='Customers'])[1]");
		By lnkCustomers_menuitem = By.xpath("(//a[@href='/Admin/Customer/List'])[1]");
		
		By btnAddnew=By.xpath("//a[@class='btn btn-primary']"); // Add new
		
		By txtEmail = By.xpath("//input[@id='Email']");
		By txtPassword=By.xpath("//input[@id='Password']");
		
		By txtcustomerRoles = By.xpath("//span[@class='select2-container select2-container--default select2-container--open']");
		
		By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
		By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
		By lstitemGuests = By.xpath("//span[@class='select2-container select2-container--default select2-container--open']//li[contains(text(),'Guests')]");
		By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
		
		By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
		
		By rdMaleGender = By.id("Gender_Male");
		By rdFemaleGender = By.id("Gender_Female");
		
		
		By txtFirstName = By.xpath("//input[@id='FirstName']");
		
		By txtLastName = By.xpath("//input[@id='LastName']");
		
		By txtDob = By.xpath("//input[@id='DateOfBirth']");
		
		By txtCompanyName = By.xpath("//input[@id='Company']");
		
		By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
		
		By btnSave = By.xpath("//button[@name='save']");
		
		//Action Methods
		
		public String getPageTitle() 
		{
			return ldriver.getTitle();
		}
		
		public void clickonCustomersMenu()
		{
			ldriver.findElement(lnkCustomers_menu).click();
		}		
		
		public void clickonCustomersMenuItem()
		{
			ldriver.findElement(lnkCustomers_menuitem).click();
		}		
		
		public void clickonAddnew()
		{
			ldriver.findElement(btnAddnew).click();
		}
		
		public void setEmail(String email)
		{
			ldriver.findElement(txtEmail).sendKeys(email);
		}
		
		public void setPassword(String password)
		{
			ldriver.findElement(txtPassword).sendKeys(password);
		}
		
		
		
		public void setManagerOfVendor(String value) 
		{
			Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
			drp.selectByVisibleText(value);		
		}
		
		
		public void setGender(String gender)
		{
			if(gender.equals("Male")) 
			{
				ldriver.findElement(rdMaleGender).click();
			}else if(gender.equals("Female"))
			{
				ldriver.findElement(rdFemaleGender).click();
			}
			else
			{
				ldriver.findElement(rdMaleGender).click();// Default
			}
		}
		
		public void setFirstName(String fname)
		{
			ldriver.findElement(txtFirstName).sendKeys(fname);
		}
		
		public void setLastName(String lname)
		{
			ldriver.findElement(txtLastName).sendKeys(lname);
		}
		
		
		public void setDob(String dob)
		{
			ldriver.findElement(txtDob).sendKeys(dob);
		}
		
		public void setCompanyName(String comname)
		{
			ldriver.findElement(txtCompanyName).sendKeys(comname);
		}
		
		public void setAdminContent(String content)
		{
			ldriver.findElement(txtAdminContent).sendKeys(content);
		}
		
	/*	public void setCustomerRoles(String role) throws InterruptedException 
		{
			JavascriptExecutor js = (JavascriptExecutor)ldriver;
			if(!role.equals("Vendors")) // If role is vendors should not delete Register as 
			{
				ldriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				//ldriver.findElement(By.xpath("//*[@id=\\'SelectedCustomerRoleIds_taglist\\']/li/span/[text()='Registered']"));
				ldriver.findElement(By.xpath("//label[@for='SelectedCustomerRoleIds']/ancestor::body/descendant::span/ul[@id='select2-SelectedCustomerRoleIds-results']")).click();
				FluentWait wait = new FluentWait(ldriver);
				
				Wait wait1 = new FluentWait(ldriver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
	                    .ignoring(NoSuchElementException.class);
			
			}
			
			
		
			
			
			
			
		//	Thread.sleep(5000);
			ldriver.findElement(txtcustomerRoles).click();
			//Thread.sleep(2000);
			WebElement listitem;
		
			
			ldriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			if(role.equals("Administrators")) 
			{
				listitem = ldriver.findElement(lstitemAdministrators);
			}
			else if(role.equals("Guests")) 
			{
				listitem = ldriver.findElement(lstitemGuests);
			}
			else if(role.equals("Registered")) 
			{
				listitem = ldriver.findElement(lstitemRegistered);
			}
			else if(role.equals("Vendors")) 
			{
				listitem = ldriver.findElement(lstitemVendors);
			}
			else
			{
				listitem=ldriver.findElement(lstitemGuests);
			}
			
			//listitem.click();
			//Thread.sleep(3000);
			//or if listitem doesnt get click we can use javascriptExecutor
		//	JavascriptExecutor js = (JavascriptExecutor)ldriver;
			
			js.executeScript("arguments[0].scrollIntoView(true);",listitem);
			
			listitem.click();
			
			 boolean b = listitem.isSelected();
      if (b) {
         System.out.println("listitem is not checked");
      }else {
         System.out.println("listitem is checked");
      }
		}
		*/
		public void clickOnSave()
		{
			ldriver.findElement(btnSave).click();
		}
		
		

}