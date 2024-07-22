package src.test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import src.test.java.basePackage.BaseClass;

public class TF_002_Dashboard  extends BaseClass{
	
	
	public TF_002_Dashboard(WebDriver driver) {
		
		this.driver=driver;
	}


	public TF_002_Dashboard dashboardpage() throws InterruptedException
	{
		
		try {
			Thread.sleep(5000);
			
			String dashboard = driver.findElement(By.xpath("(//p)[1]")).getText();
			
			System.out.println(dashboard);
			
			reportStep("verifying dashboard is printed", "Pass");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			reportStep("dashboard is not printed", "fail");
		}
		
		return this;
	}
	
	
	public TF_002_Dashboard clickOnFirstClass()
	{
		try {
			
			driver.findElement(By.xpath("((//p)[1]/following::div/div)[3]/div[1]")).click();
			
			reportStep("First class has been clicked", "Pass");
			
			return this;
		} catch (Exception e) {
			reportStep("First class has not been clicked", "fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public TF_002_Dashboard verifyClasspage() throws InterruptedException
	{
		try {
			Thread.sleep(5000);
			
			String verifyclass = driver.findElement(By.xpath("(//p)[1]")).getText();
			
			Assert.assertEquals(verifyclass, "Class");
			
			reportStep("Class header is verified successfully", "Pass");
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			reportStep("Class header is not verified successfully", "fail");
		}

		return this;
	}
	
	

}
