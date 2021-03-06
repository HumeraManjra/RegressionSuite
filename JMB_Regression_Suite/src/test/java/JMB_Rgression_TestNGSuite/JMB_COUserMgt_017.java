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


public class JMB_COUserMgt_017 
//This test is testing whether a employer can click on INVITE USER button and redirecetd to the user management page and a new user should be add in the list.
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
	public void  VerifyNewUserAddedToTheList()  
		{
		WebElement InviteUserButton = driver.findElement(By.linkText("INVITE USER"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", InviteUserButton);   // Click on Invite Button			
		driver.findElement(By.xpath("//input[@id='emailID']")).sendKeys ("rise@mailinator.com"); //Enter email ID	
		driver.findElement(By.xpath("//span[contains(text(),'Select Role')]")).click(); //Click on Role dropdown box
		driver.findElement(By.xpath("//div[@id='userRoleId']//li[2]//a[1]")).click(); //Select Role	
		driver.findElement(By.xpath("//span[contains(text(),'Select Approve Time')]")).click();  		//Click on Approve time dropdown box
		driver.findElement(By.xpath("//div[@class='bfh-selectbox mysaved open']//div[@class='bfh-selectbox-options']//div//a[contains(text(),'Yes')]")).click();//Select time		
		// Enter 100 char in Message text box
		driver.findElement(By.xpath("//textarea[@id='job-description']")).sendKeys("Welcome!!!!!");
		driver.findElement(By.xpath("//button[contains(text(),'INVITE USER')]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
		//After Invite, User Should be added to the List, User list should be increased from 1 to 2		
		List<WebElement> UserList = driver.findElements(By.xpath("//table[@class='table table-hover']//tbody//tr"));
		int size = UserList.size();	
		Assert.assertEquals(size, 2);
		System.out.println("Test Failed, User was not added, User List  remain unchanged as 1 user");
		}

	@AfterClass()
	public void  Teardown()
	{
		driver.close();
	}
	
}
