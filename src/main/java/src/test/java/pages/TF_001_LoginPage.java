package src.test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import src.test.java.basePackage.BaseClass;

public class TF_001_LoginPage extends BaseClass {
	
	
	public TF_001_LoginPage(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public TF_001_LoginPage username(String username) {
		
		try {
			driver.findElement(By.name("email")).sendKeys(username);
			
			reportStep("Entering the username is passed", "Pass");
		}
		
		catch(Exception e )
		{
			 e.printStackTrace();
			 
			 reportStep("Entering the username is failed", "fail");
			
		}
		return this;
	}

	public TF_001_LoginPage password(String password) {
		
		try {
			driver.findElement(By.name("password")).sendKeys(password);
			
			reportStep("Entering the password is passed", "Pass");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			reportStep("Entering the password is failed", "fail");
		}
		
		return this;
	}

	public TF_001_LoginPage loginButton() {
		try {
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			reportStep("Clicking the login Button is passed", "Pass");
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			reportStep("Clicking the login Button is failed", "fail");
		}
		
		return this;
	}

	public TF_002_Dashboard verifyloginStatus() {
		
		try {
			String loginStatus = driver.findElement(By.xpath("//div[@role='presentation']/div/div[2]")).getText();
			
			String emailWarning = driver.findElement(By.xpath("//form/div[1]/div[1]/following::span[1]")).getText();
			
			String passwordwarining = driver.findElement(By.xpath("//form/div[2]/div[1]/following::span[1]")).getText();
			

			System.out.println(loginStatus);
			
			System.out.println(emailWarning);
			
			System.out.println(passwordwarining);

			if(loginStatus.equals("Login successful"))
			{
				Assert.assertEquals(loginStatus, "Login successful");
			}
			else if(loginStatus.equals("Invalid Email"))
			{
				Assert.assertEquals(loginStatus, "Invalid Email");
				
			}
			
			else if(loginStatus.equals("Incorrect email or password"))
			{
				Assert.assertEquals(loginStatus, "Incorrect email or password");
			}
			
			else if (emailWarning.equals("* Email must be a valid email"))
			{
				Assert.assertEquals(emailWarning, "* Email must be a valid emai");
			}
			
			else if(passwordwarining.equals("* Password must be at least 6 characters and include at least one letter and one number"))
			{
				Assert.assertEquals(passwordwarining, "* Password must be at least 6 characters and include at least one letter and one number");
			}
			
			reportStep("Login message validated is passed", "Pass");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Login message validated is failed", "fail");
		}
		
		return new TF_002_Dashboard(driver);
	}

}
