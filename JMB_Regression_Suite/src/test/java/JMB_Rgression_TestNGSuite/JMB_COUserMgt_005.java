package JMB_Rgression_TestNGSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class JMB_COUserMgt_005 

//This test is testing whether a company can redirecetd to the user management page and all the fields are available on this page i.e "Status", "Role", "Approve Time", "Search", "Invite User".
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
			}	
	
	@Test(priority = 2)
			public void NavigateToUserMngmntPage()
			{
			  Actions act = new Actions(driver);
			 WebElement sidelogo = driver.findElement(By.xpath("//div[@class='sidelogo_div']"));
			 WebElement Setting = driver.findElement(By.xpath("//span[contains(text(),'Setting')]"));
			 WebElement UserMngmnt= driver.findElement(By.xpath("//a[contains(text(),'User Management (Admin)')]"));
			 act.moveToElement(sidelogo).moveToElement(Setting).moveToElement(UserMngmnt).click().build().perform();
		

			WebElement UserField =  driver.findElement(By.xpath("//th[contains(text(),'User')]"));
			WebElement RoleField =  driver.findElement(By.xpath("//th[contains(text(),'Role')]"));
			WebElement AppTimeField = driver.findElement(By.xpath("//th[contains(text(),'Approve Time')]"));
			WebElement LastLoggedInField = driver.findElement(By.xpath("//th[contains(text(),'Last Logged In')]"));
			WebElement ActionField = driver.findElement(By.xpath("//th[contains(text(),'Action')]"));
			
			SoftAssert softassert = new SoftAssert();
					
			softassert.assertTrue(UserField.isDisplayed());
			softassert.assertTrue(RoleField.isDisplayed());
			softassert.assertTrue(AppTimeField.isDisplayed());
			softassert.assertTrue(LastLoggedInField.isDisplayed());
			softassert.assertTrue(ActionField.isDisplayed());
			
			System.out.println("All fields have been displayed, Test Passed");
	
			
	}
	
	@AfterClass()
	public void  Teardown()
	{
		driver.close();
	}

}
