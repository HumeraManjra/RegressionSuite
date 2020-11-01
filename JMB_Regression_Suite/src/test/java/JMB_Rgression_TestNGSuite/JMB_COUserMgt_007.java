package JMB_Rgression_TestNGSuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//This test is testing whether an employer can click on ROLES  arrow  button and dropdown list should be opened and all categories "ADMIN","SENIOR HR","HR","ACCOUNTS","HR MANAGER"  should be appeared and selectable.
public class JMB_COUserMgt_007 
{
	WebDriver driver;

	@BeforeClass()
	public void Setup ()
	{
			System.setProperty("webdriver.chrome.driver", "./Resource\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get("Enter Your URL Here");				
	}
			
	@Test(priority = 1)
	@Parameters({"email", "password"})
			public void Login(String email, String password)
			{
					driver.findElement(By.xpath("//li[@class='actionbtn']//a[@class='btns ripple'][contains(text(),'Login')]")).click();
					driver.findElement(By.id("email")).sendKeys(email);
					driver.findElement(By.id("password-field")).sendKeys(password);
					driver.findElement(By.xpath("//input[@id='sbBt']")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
			}	
	
	@Test(priority = 2)
			public void NavigateToUserMngmntPage() 
			{
			Actions act = new Actions(driver);
			WebElement sidelogo = driver.findElement(By.xpath("//div[@class='sidelogo_div']"));
			WebElement Setting = driver.findElement(By.xpath("//span[contains(text(),'Setting')]"));
			WebElement UserMngmnt= driver.findElement(By.xpath("//a[contains(text(),'User Management (Admin)')]"));
			act.moveToElement(sidelogo).moveToElement(Setting).moveToElement(UserMngmnt).click().build().perform();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
	
	@Test(priority = 3)
		public void RoleDropDownMenu()
		{
				WebElement RoleDD = driver.findElement(By.xpath("//span[contains(text(),'Roles')]"));
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript("arguments[0].click();", RoleDD);
	
 List<WebElement> Role_DropDown_Menu = driver.findElements(By.xpath(" //div[@class='bfh-selectbox selheightauto open']//div[@class='bfh-selectbox-options']//div//ul//li//a"));
					   
			   for (WebElement Elements :Role_DropDown_Menu )
			   {
				   		String Values = Elements.getText();
				   		System.out.println(Values);
			   }
			   
			   int ActualSize = Role_DropDown_Menu.size();
				 System.out.println("Size of  Role Drop down List  is:  " +ActualSize);		
				Assert.assertEquals(ActualSize, 6);
			    System.out.println("Drop down list size matched, Test passed");	    			  
		}

	
	@AfterClass()
	public void  Teardown()
	{
		driver.close();
	}

}
