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

public class TC_007_RecordingScenarioByUser_TellPortal extends ProjectSpecifications{
	
	
	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123","GoodMorning.wav" }, 
			{"ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123","ScenarioCheck.wav"},
			{"ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123","ScenarioCheck3.wav"}
			
		};
	}
	
	@Test(dataProvider = "loginData")
	public void recordingScenarioByUser(String usernamevalues, String passwordvalues, String filename) throws InterruptedException, AWTException
	{
		
		WebElement email = driver.findElement(By.xpath("//input[@name='emailOrPhoneNumber']"));

		// Entering the username
		email.sendKeys(usernamevalues);

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

		// Entering the password
		password.sendKeys(passwordvalues);

		// Click login Button
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		
		 // Validate the logins
		try {
			if (driver.findElement(By.xpath("//div[@role='presentation']/div/child::div[2]")).isDisplayed() == true) {

				WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation']/div/child::div[2]")));

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
		
		WebElement Scenario = driver.findElement(By.xpath("//span[text()='Scenario']"));
		
		Assert.assertEquals(Scenario.getText(), "Scenario");
		
		driver.navigate().to("https://dev.tell.navadhiti.com/test");
		
		Thread.sleep(5000);
		
		//Click on Speak Button	
		WebElement speak= driver.findElement(By.xpath("//button[@aria-label='Speak']"));
	    
	    jsExecutor.executeScript("arguments[0].click();", speak);
	    
	    Thread.sleep(2000);
	    
	    Robot robot = new Robot();

        //Chrome Permisson Prompts to Allow
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
		
		mainPackageTellPortal.AudioFileReader.readAudiFile(filename);
		
		//Click Pause Button
		driver.findElement(By.xpath("//*[@data-testid='PauseIcon']")).click();

		Thread.sleep(5000);
		
		//Getting the score
		WebElement score =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Score']/div/p")));
		
		System.out.println("The Score is "+score.getText());
		
		
		
	}		
	

}
