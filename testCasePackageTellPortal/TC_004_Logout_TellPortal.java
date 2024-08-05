package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_004_Logout_TellPortal extends ProjectSpecifications {

	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123" }, };
	}

	@Test(dataProvider = "loginData")
	public void validateLogOut(String usernamevalues, String passwordvalues) throws InterruptedException {

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

		// Assert on Scenario Page
		WebElement Scenario = driver.findElement(By.xpath("//span[text()='Scenario']"));

		Assert.assertEquals(Scenario.getText(), "Scenario");

		Thread.sleep(3000);

		WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Profile'])[1]")));

		// Click on Profile
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", profile);

		// Trying to click on Logout Button
		try {
			WebElement logoutoverlapping = driver.findElement(By.xpath("(//ul[@role='menu'])[5]"));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].style.zIndex = '0';", logoutoverlapping);
			
			WebElement logout = driver.findElement(By.xpath("//body/div[5]/div[3]/ul[1]"));
			
			logout.click();

		} catch (ElementClickInterceptedException e) {
			
			WebElement logout = driver.findElement(By.xpath("//body/div[5]/div[3]/ul[1]"));
			
			Actions actions = new Actions(driver);
			
			actions.moveToElement(logout).click().perform();

		} catch (ElementNotInteractableException e) {
			
			WebElement logout = driver.findElement(By.xpath("//body/div[5]/div[3]/ul[1]"));
			
			int x=logout.getLocation().getX();
			int y = logout.getLocation().getY();
		
             Actions actions = new Actions(driver);
			
			actions.moveToElement(logout, x, y).click().perform();
			
			e.printStackTrace();
	
		}catch (Exception e)
		{
           WebElement logout = driver.findElement(By.xpath("//body/div[5]/div[3]/ul[1]"));
			
			JavascriptExecutor javascript = (JavascriptExecutor) driver;
			
			javascript.executeScript("arguments[0].click();", logout);
			
			e.printStackTrace();
		}
		
		 wait.until(ExpectedConditions.urlToBe("https://dev.tell.navadhiti.com/"));

		WebElement signup = driver.findElement(By.xpath("//a[text()=' Signup']"));

		Assert.assertEquals(signup.getText(), "Signup");

		driver.navigate().to("https://dev.tell.navadhiti.com/practice");

		Assert.assertEquals(signup.getText(), "Signup");

	}

}