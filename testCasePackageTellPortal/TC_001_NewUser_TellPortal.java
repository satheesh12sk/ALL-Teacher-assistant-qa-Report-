package testCasePackageTellPortal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			 { "Stike Barnard","Stike@buzzfeed.com","3776147653","P@ssw0rd"},
			 { "Ethan Barnard","Ethan@buzzfeed.com","6614765322","P@ssw0rd"},
				{ "", "rakeshkumar","9757555205", "bM3\"=nxbA"},
				{ "Julieta Hanscome","ra","","Riyon1@*123" },
				{ "","ra","","bM3\"=nxbA" },
				{"Rakesh Kumar","rakeshkumargmail.com","975755525","" },
				{ "", "rakeshkumar@gmail.com","","" }
				
		};
	}
	
	public String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

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
		
		    String passwordvalue = password.getAttribute("value");
			
			String emailvalue = email.getAttribute("value");
			
			String phoneNumberValue =phoneNumber.getAttribute("value");

			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation']/div/div[2]")));
				
				// Verify ToastMessage
				String verifyToastMessage = driver.findElement(By.xpath("//div[@role='presentation']/div/div[2]")).getText();
				
				if(verifyToastMessage.equals("Registered Successfully"))
				{

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
					
					Thread.sleep(2000);
					
					//Click on complete Button
					driver.findElement(By.xpath("//button[text()='Complete']")).click();
					
					Thread.sleep(8000);
					
					WebElement Scenario = driver.findElement(By.xpath("//span[text()='Scenario']"));
		
					
					Assert.assertEquals(Scenario.getText(), "Scenario");
					
				}
				
				else if(verifyToastMessage.equals("Account Already Exists"))
				{
					Assert.assertEquals(verifyToastMessage, "Account Already Exists");
					
				}
				
				
			}catch(Exception e)
			{
				 if(name.getAttribute("value").isEmpty())
					{
						WebElement usernamewarning= driver.findElement(By.xpath("//span[text()='Full name is required']"));
				    	 
				    	 String usernamefieldwarning = usernamewarning.getText().trim().replaceFirst("^\\*", "").trim();
				    	 
				    	 Assert.assertEquals(usernamefieldwarning, "Full name is required");
						
					}
					
				else if(!isValidEmail(emailvalue))
			    	 {
			    		 WebElement emailwarning= driver.findElement(By.xpath("//span[text()='Invalid email']"));
			    		 
			    		String emailwarningfield= emailwarning.getText().trim().replaceFirst("^\\*", "").trim();
			    		
			    		Assert.assertEquals(emailwarningfield, "Invalid email");
			    		
			    	 }
					
				else if(phoneNumber.getAttribute("value").isEmpty())
			    	 {
			    		 WebElement phonewarning= driver.findElement(By.xpath("//span[text()='Phone Number is required']"));
				    	 
				    	 String phonenumberfieldwarning = phonewarning.getText().trim().replaceFirst("^\\*", "").trim();
				    	 
				    	 Assert.assertEquals(phonenumberfieldwarning, "Phone Number is required");	 
			    		 
			    	 }
			    	 
			    	 
				 else if (phoneNumberValue.length()<9)
			    	 {
			    		 
			    		 WebElement phonenumberlength = driver.findElement(By.xpath("//span[text()='Phone number must be 10 digits']"));
			    		 
			    		 String phonelengthwarning = phonenumberlength.getText().trim().replaceFirst("^\\*", "").trim();
							
					     Assert.assertEquals(phonelengthwarning,"Phone number must be 10 digits");
			    		 
			    	 }
			    	 
				 else if(password.getAttribute("value").isEmpty())
			    	 {
			    		 WebElement passwordfield= driver.findElement(By.xpath("//span[text()='Password is required']"));
				    	 
				    	 String passwordfieldwarning = passwordfield.getText().trim().replaceFirst("^\\*", "").trim();
				    	 
				    	 Assert.assertEquals(passwordfieldwarning, "Password is required");
			    		 
			    	 }
			    	 
			    	 
			    	 else if(!isValidPassword(passwordvalue))
			    	 {
			    		 WebElement passwordValidateWarningMessage=driver.findElement(By.xpath("//span[text()='Password must be strong: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number, and one special character']"));
			    		 
		               String passwordValidateWarning = passwordValidateWarningMessage.getText().trim().replaceFirst("^\\*", "").trim();
				    	 
				    	 Assert.assertEquals(passwordValidateWarning, "Password must be strong: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number, and one special character");
			    		 
			    	 }
			     
			    else if(name.getAttribute("value").isEmpty() && phoneNumber.getAttribute("value").isEmpty() && password.getAttribute("value").isEmpty())
			     {
		            WebElement usernamewarning= driver.findElement(By.xpath("//span[text()='Full name is required']"));
			    	 
			    	 String usernamefieldwarning = usernamewarning.getText().trim().replaceFirst("^\\*", "").trim();
			    	 
			    	 Assert.assertEquals(usernamefieldwarning, "Full name is required");
			    	 
			    	 
			    	 WebElement phonewarning= driver.findElement(By.xpath("//span[text()='Phone Number is required']"));
			    	 
			    	 String phonenumberfieldwarning = phonewarning.getText().trim().replaceFirst("^\\*", "").trim();
			    	 
			    	 Assert.assertEquals(phonenumberfieldwarning, "Phone Number is required");
			    	 
			    	 
			    	 
			    	 WebElement passwordfield= driver.findElement(By.xpath("//span[text()='Password is required']"));
			    	 
			    	 String passwordfieldwarning = passwordfield.getText().trim().replaceFirst("^\\*", "").trim();
			    	 
			    	 Assert.assertEquals(passwordfieldwarning, "Password is required");

			     }
				e.printStackTrace();
			}

		}
	
	public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
	
	private boolean isValidEmail(String emailvalue) {
        // Simple check for email validity; you might want to use a more robust validation
        return emailvalue != null && emailvalue.contains("@") && emailvalue.contains(".");
    }
	

}
