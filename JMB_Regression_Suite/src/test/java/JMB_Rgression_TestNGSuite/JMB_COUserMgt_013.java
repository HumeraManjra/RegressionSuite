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

public class JMB_COUserMgt_013
{
	
	//This test is testing whether an employer can click on roles field button and dropdown list should be opened and select the particular role for valid employer.
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
	public void  InviteUser_SelectRole()  
		{	
		WebElement InviteUserButton = driver.findElement(By.linkText("INVITE USER"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", InviteUserButton);   // Click on Invite Button
		
		driver.findElement(By.xpath("//span[contains(text(),'Select Role')]")).click();  //Click on Role Drop down Menu

		driver.findElement(By.xpath("//div[@id='userRoleId']//li[2]//a[1]")).click();  // Select "HR" 

		WebElement SelectedValue = driver.findElement(By.className("bfh-selectbox-option")); // Find HR Element and save into variable 
		String Role = SelectedValue.getText();
		
		Assert.assertEquals(Role, "HR");
		System.out.println("Selected value==" +Role);					
		}
	
	@AfterClass()
	public void  Teardown()
	{
		driver.close();
	}
		
}
