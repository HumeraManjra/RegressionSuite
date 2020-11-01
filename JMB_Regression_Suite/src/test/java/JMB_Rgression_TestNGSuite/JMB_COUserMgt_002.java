package JMB_Rgression_TestNGSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// This test case is testing whether a company can login with Invalid Username and Invalid password.

public class JMB_COUserMgt_002
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
	public void Login_With_NegativeInput()
	{
		driver.findElement(By.xpath("//li[@class='actionbtn']//a[@class='btns ripple'][contains(text(),'Login')]")).click();
		driver.findElement(By.id("email")).sendKeys("Invalidemailid@gmail.com");
		driver.findElement(By.id("password-field")).sendKeys("IncorrectPassword123");
		driver.findElement(By.xpath("//input[@id='sbBt']")).click();

		WebElement InvalidCredentialMsg = driver.findElement(By.id("linked-expired-msz"));
		Assert.assertTrue(InvalidCredentialMsg.isDisplayed());
		System.out.println(InvalidCredentialMsg.getText());
		System.out.println("Invalid Credential message was displayed, Test Passed");
		
	}

@AfterClass()
public void  Teardown()
{
driver.close();
}


}
