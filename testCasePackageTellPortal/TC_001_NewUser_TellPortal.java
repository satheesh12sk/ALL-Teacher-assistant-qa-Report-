package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_001_NewUser_TellPortal extends ProjectSpecifications {
	
	
	@DataProvider(name = "newUserData")
	public Object[][] testData() {
		return new Object[][] { 
			    { "Sonesh","Sonesh@gmail.com","9798588742","P@ssw0rd"},
				
		};
	}

	@Test(dataProvider = "newUserData")
	public void validateSignUp(String namedetails, String emaildetails, String phonenumberdetails, String passworddetails) throws InterruptedException {
		
		// Click on Signup
		driver.findElement(By.xpath("//a[text()=' Signup']")).click();

		// Enter name
		WebElement name=driver.findElement(By.name("fullName"));
		
		name.sendKeys(namedetails);

		// Enter email
		WebElement email=driver.findElement(By.name("email"));
		
		email.sendKeys(emaildetails);

		// Enter phonenumber
		WebElement phoneNumber=driver.findElement(By.xpath("//input[@name='phoneNumber']"));
		
		phoneNumber.sendKeys(phonenumberdetails);

		// Enter password
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		
		password.sendKeys(passworddetails);

		// Click submit
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation']/div/div[2]")));
		
		// Verify ToastMessage
		String verifyToastMessage = driver.findElement(By.xpath("//div[@role='presentation']/div/div[2]")).getText();

		Assert.assertEquals(verifyToastMessage, "Registered Successfully");
		
		Thread.sleep(2000);

		// Verify landed on language selection page
		WebElement onBoardingPage = driver.findElement(By.xpath("//h2[text()='What is your mother tongue?']"));
		
		// Verify landed on proficiency selection page
		Assert.assertEquals(onBoardingPage.getText(),"What is your mother tongue?");
		
		// Selecting Language
		driver.findElement(By.xpath("//*[text()='Tamil']")).click();
	
		// Click Next
		driver.findElement(By.xpath("//button[text()='Next']")).click();
	

		//Proficency Header
		WebElement proficiency = driver.findElement(By.xpath("//h2[text()='How fluent are you in English?']"));

		Assert.assertEquals(proficiency.getText(),"How fluent are you in English?");
		
		//Select Level
		driver.findElement(By.xpath("//*[text()='Beginner']")).click();
				
		// Click Next
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		
		// Type what you heard
		WebElement speakWhatyouHeardheader = driver.findElement(By.xpath("//h2[text()='Please type what you heard']"));
		
		//Assert Speak What you heard page
		Assert.assertEquals(speakWhatyouHeardheader.getText(),"Please type what you heard");
		
		// Send Text
		driver.findElement(By.xpath("//textarea[1]")).sendKeys("Could you please provide me name and DOB");
		
		//Click Next Button
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		
		//Verify the Hear and speak page 
		WebElement SpeakwithWhatyouHeard =driver.findElement(By.xpath("//h2[text()='Please speak what you heard']"));
		
		Assert.assertEquals(SpeakwithWhatyouHeard.getText(),"Please speak what you heard");
		
		//Click on complete Button
		driver.findElement(By.xpath("//button[text()='Complete']")).click();
		
		WebElement Scenario = driver.findElement(By.xpath("//span[text()='Scenario']"));
		
		Assert.assertEquals(Scenario.getText(), "Scenario");
	     
	     
	     
		
	}
	

}
