package testCasePackageTellPortal;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_010_PracticeTest_TellPortal extends ProjectSpecifications{
	
	
	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123", "GoodMorning.wav"}
		
		};
	}
	
	
	@Test(dataProvider = "loginData")
	public void practiceTest(String usernamevalues, String passwordvalues, String filename) throws InterruptedException, AWTException
	{
		WebElement email = driver.findElement(By.xpath("//input[@name='emailOrPhoneNumber']"));

		// Entering the username
		email.sendKeys(usernamevalues);

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

		// Entering the password
		password.sendKeys(passwordvalues);

		// Click login Button
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		

		try {
			if (driver.findElement(By.xpath("//div[@role='presentation']/div/child::div[2]")).isDisplayed() == true) {

				WebElement toastMessage = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@role='presentation']/div/child::div[2]")));

				if (toastMessage.getText().equalsIgnoreCase("Login Successful"))

				{
					Assert.assertEquals(toastMessage.getText(), "Login Successful");
				}

				else if (toastMessage.getText().equalsIgnoreCase("Invalid Credentials")) {

					Assert.assertEquals(toastMessage.getText(), "Invalid Credentials");
				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		
		Thread.sleep(5000);
		
      WebElement listen= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Listen']")));
      
      jsExecutor.executeScript("arguments[0].click();", listen);
		
	   Thread.sleep(8000);
	   
	   WebElement speak= driver.findElement(By.xpath("//button[@aria-label='Speak']"));
	    
	    jsExecutor.executeScript("arguments[0].click();", speak);
	    
	    Thread.sleep(2000);
	    
	    Robot robot = new Robot();

        // Perform TAB key presses
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
		
        mainPackageTellPortal.AudioFileReader.readAudiFile(filename);
		
        //Click on pause Button
        
		driver.findElement(By.xpath("//*[@data-testid='PauseIcon']")).click();

		Thread.sleep(2000);
		
		//Click on Play Button
		driver.findElement(By.xpath("//button[@aria-label='Play']")).click();
		
		Thread.sleep(5000);
		
	}

}
