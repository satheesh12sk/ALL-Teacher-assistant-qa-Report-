package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_006_ListenAudioPlayCheck_TellPortal extends ProjectSpecifications{
	
	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123" }, };
	}
	
	@Test(dataProvider = "loginData")
	public void listenAudioCheck(String usernamevalues,String passwordvalues) throws InterruptedException
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
		
		driver.findElement(By.xpath("(//button)[15]/following::p[text()='Listen']")).click();
		
		Thread.sleep(8000);

	}

}
