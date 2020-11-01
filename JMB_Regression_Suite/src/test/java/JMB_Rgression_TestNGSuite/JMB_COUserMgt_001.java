package JMB_Rgression_TestNGSuite;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


//This test case is testing whether a company can login with valid User name and valid password. (Positive Test case)

public class JMB_COUserMgt_001{

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
		public void Login_With_PositiveInput(String email, String password)
		{
				driver.findElement(By.xpath("//li[@class='actionbtn']//a[@class='btns ripple'][contains(text(),'Login')]")).click();
				driver.findElement(By.id("email")).sendKeys(email);
				driver.findElement(By.id("password-field")).sendKeys(password);
				driver.findElement(By.xpath("//input[@id='sbBt']")).click();
				String Actltitle = driver.getTitle();
				String Exptitle = "Dashboard";		
				Assert.assertEquals(Actltitle, Exptitle);
				System.out.println("Page Title matched, Login successfully completed");
				
		}
		
		@AfterClass()
		public void  Teardown()
		{
			driver.close();
		}
		
		

}
