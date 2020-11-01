package JMB_Rgression_TestNGSuite;

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

public class JMB_COUserMgt_014 
//This test is testing whether an employer can click on Approve Time field button and dropdown list should be opened and select the particular value.

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
	}
	
	
	@Test(priority = 3)
	public void  InviteUser_ApproveTimeDropDown()  
		{
		WebElement InviteUserButton = driver.findElement(By.linkText("INVITE USER"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", InviteUserButton);   // Click on Invite Button
		
		driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys ("rise@mailinator.com"); //Enter email ID
		driver.findElement(By.xpath("//span[contains(text(),'Select Role')]")).click(); //Click on Role dropdown box
		driver.findElement(By.xpath("//div[@id='userRoleId']//li[2]//a[1]")).click(); //Select Role
		driver.findElement(By.xpath("//span[contains(text(),'Select Approve Time')]")).click();  		//Click on Approve time dropdown box
		driver.findElement(By.xpath("//div[@class='bfh-selectbox mysaved open']//div[@class='bfh-selectbox-options']//div//a[contains(text(),'Yes')]")).click();//Select time
		
//Verify "YES"  in approved time drop down menu was selected
String Value = driver.findElement(By.xpath("//span[contains(text(),'Yes')]")).getText();
Assert.assertEquals(Value, "Yes");
System.out.println("Yes was selected from Approved time drop down menu, test passed");
		
		}
	
	@AfterClass()
	public void  Teardown()
	{
		driver.close();
	}
		
}