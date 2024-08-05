package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;




public class TC_005_ScenarioMoving_TellPortal  extends ProjectSpecifications{
	
	public int IntialScenarioInt;
	
	public int EndingScenarioInt;
	
	public int levelnumber=1;
	
	public int NosofScenario; 
	
	@DataProvider(name = "loginData")
	public Object[][] testData() {
		return new Object[][] { { "ramesharavindhkarthikeyan@gmail.com", "Riyon1@*123" }, };
	}
	
	@Test(dataProvider = "loginData")
	public void validatingScenarioChanging(String usernamevalues,String passwordvalues) throws InterruptedException
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
		
		Thread.sleep(5000);
		
		
		String scenarioNumbers = driver.findElement(By.xpath("((//h6)[1]/span/following::div[1]/span)[1]")).getText();
		 
		System.out.println(scenarioNumbers);
		
		
		 String[] split = scenarioNumbers.split("/");
		 
		
		 for(int i=0;i<=split.length;i++)
		 {
			 if(i==0)
			 {
				 String converttonumber = split[i];
				 IntialScenarioInt = Integer.parseInt(converttonumber);
			 }
			 
			 if(i==1)
			 {
				 String converttonumber = split[i];
				 
				NosofScenario = Integer.parseInt(converttonumber);
				 
				EndingScenarioInt= 2*NosofScenario-1;
			 }
		 }

		// Click on Next Button 
		 for(int i=1;i<=EndingScenarioInt;i++)
		 {
				
				for(int j=i;j==i;j++)
				{	
					Thread.sleep(2000);
					
					System.out.println();
					
						System.out.println(driver.findElement(By.xpath("(//p)[2]")).getText());
						
						System.out.println(driver.findElement(By.xpath("((//p)[2]/following::div)[2]/span")).getText());
				}
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Next']"))).click();
				
				if(i==EndingScenarioInt)
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Previous']"))).click();
					break;
				}
				
		 }
		 
		// Click on Previous Button 
		 do
		 {
			 Thread.sleep(2000);
				
				System.out.println();
				
					System.out.println(driver.findElement(By.xpath("(//p)[2]")).getText());
					
					System.out.println(driver.findElement(By.xpath("((//p)[2]/following::div)[2]/span")).getText());
					
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Previous']"))).click();
		 }
		 
		 while(driver.findElement(By.xpath("//button[@aria-label='Previous']")).isEnabled());
		 
		 
		try
		{
			String levelverify = "//button[text()='Click to attempt your level ']";
			
			 String levelverifyNumber = driver.findElement(By.xpath(levelverify)).getText();	
			 
			 Assert.assertEquals(levelverifyNumber, "Click to attempt your level 1");

		}catch(Exception e)
		{
			Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Good morning,How can I help you today?']")).getText(),"Good morning,How can I help you today?");
			
			e.printStackTrace();
			
		}
			
	}

}
