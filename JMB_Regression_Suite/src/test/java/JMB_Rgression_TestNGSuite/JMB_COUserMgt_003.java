package JMB_Rgression_TestNGSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


//This test case is testing whether a company can login with valid credentials and navigate to company dashboard.

public class JMB_COUserMgt_003 

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
					
			@Test()
			@Parameters({"email", "password"})
			public void NavigateToCompDashboard(String email, String password)
			{
					driver.findElement(By.xpath("//li[@class='actionbtn']//a[@class='btns ripple'][contains(text(),'Login')]")).click();
					driver.findElement(By.id("email")).sendKeys(email);
					driver.findElement(By.id("password-field")).sendKeys(password);
					driver.findElement(By.xpath("//input[@id='sbBt']")).click();	
					
					WebElement PageHeading = driver.findElement(By.xpath("//div[@class='dash_page_heading suggestionheading']"));
					Assert.assertTrue(PageHeading.isDisplayed());
					System.out.println(PageHeading.getText());
					System.out.println("Company Dashbaord Page Heading is displayed,  Test Passed");
			}
			
			@AfterClass()
			public void  Teardown()
			{
				driver.close();
			}
	
	
}
