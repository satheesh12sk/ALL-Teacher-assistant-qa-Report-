package testCasePackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
	
	
	@Test
	public void validatelogins()
	{
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://allteacher.navadhiti.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.name("email")).sendKeys("demoteacher1@gmail.com");
		
		driver.findElement(By.name("password")).sendKeys("zxcASD123@!$");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String loginStatus = driver.findElement(By.xpath("//div[text()='Login successful']")).getText();
		
		System.out.println(loginStatus);
		
		Assert.assertEquals(loginStatus, "Login successful");
	}

}
