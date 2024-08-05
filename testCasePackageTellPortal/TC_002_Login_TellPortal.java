package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_002_Login_TellPortal extends ProjectSpecifications {

	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123" }, 
		{ "r", "R" },
				{ "ramesharavindhk", "Riyon1@*123" }, 
				{ "ramesharavindhkarthikeyan@gmail.com", "Riyon" },
				{ "ramesharavindhkarthikeyan@gmail.com", "" }, 
				{ "", "Riyon1@*123" }, 
				{ "", "" } };
	}

	@Test(dataProvider = "loginData")
	public void validatelogin(String usernamevalues, String passwordvalues) throws InterruptedException {

		WebElement email = driver.findElement(By.xpath("//input[@name='emailOrPhoneNumber']"));

		// Entering the username
		email.sendKeys(usernamevalues);

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

		// Entering the password
		password.sendKeys(passwordvalues);

		// Click login Button
		driver.findElement(By.xpath("//button[text()='Login']")).click();		

		// Trying for validate login 
		try {
			if (driver.findElement(By.xpath("//div[@role='presentation']/div/child::div[2]")).isDisplayed() == true) {

				WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation']/div/child::div[2]")));

				if (toastMessage.getText().equalsIgnoreCase("Login Successful"))

				{
					Assert.assertEquals(toastMessage.getText(), "Login Successful");

					String onBoardingHeader = "What is your mother tongue?";

					WebElement onBoardingPage = driver.findElement(By.xpath("//h2[text()='What is your mother tongue?"));

					Assert.assertEquals(onBoardingPage.getText(), onBoardingHeader);
				}

				else if (toastMessage.getText().equalsIgnoreCase("Invalid Credentials")) {

					Assert.assertEquals(toastMessage.getText(), "Invalid Credentials");
				}

			}
		} 
		catch (Exception e) {
			
			// Checking email field is empty	
			if (email.getAttribute("value").isEmpty()) {

				WebElement emailWarning = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/span[1]"));

				if (emailWarning.getText().trim().replaceFirst("^\\*", "").trim().equalsIgnoreCase("Email or phone number is required")) {

					Assert.assertEquals(emailWarning.getText().trim().replaceFirst("^\\*", "").trim(),"Email or phone number is required");
				}
			}
			
			// Checking password field is empty
			else if (password.getAttribute("value").isEmpty()) {

				WebElement passwordWarning = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/span[1]"));

				if (passwordWarning.getText().trim().replaceFirst("^\\*", "").trim().equalsIgnoreCase("Password is required")) {

					Assert.assertEquals(passwordWarning.getText().trim().replaceFirst("^\\*", "").trim(),"Password is required");
				}
			}


		}

	}

}
