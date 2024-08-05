package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_008_VoiceTest_TellPortal extends ProjectSpecifications{
	
	
	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123", "male"}, 
			//{ "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123", "Female"},
			//{ "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123", "Male"},
		};
	}
	
	@Test(dataProvider = "loginData")
	public void voiceTest(String usernamevalues, String passwordvalues, String voices) throws InterruptedException {
		
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
		
		Thread.sleep(3000);
		
		WebElement disappearingVoice = driver.findElement(By.xpath("//*[@aria-label='select voice']"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].style.zIndex = '0';", disappearingVoice);
		
		WebElement selectVoice = driver.findElement(By.xpath("(//*[@aria-label='select voice'])[2]"));
		selectVoice.click();
		
		Thread.sleep(5000);
		
		WebElement disappearingElement = driver.findElement(By.xpath("//b[text()='"+voices+"']"));
		
       ((JavascriptExecutor) driver).executeScript("arguments[0].style.zIndex = '0';", disappearingElement);
		
		WebElement GenderSelection = driver.findElement(By.xpath("(//b[text()='"+voices+"'])[2]"));
		
		GenderSelection.click();
		
		WebElement Listen=driver.findElement(By.xpath("//button[@aria-label='Listen']"));
		
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", Listen);
		
		Listen.click();
		
		Thread.sleep(8000);

		
		
	}

}
