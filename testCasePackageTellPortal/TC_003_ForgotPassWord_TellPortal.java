package testCasePackageTellPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackageTellPortal.ProjectSpecifications;

public class TC_003_ForgotPassWord_TellPortal extends ProjectSpecifications {
	
	
	@DataProvider(name = "forgotpassword")
    public Object[][] testData() {
        return new Object[][] {
       	{"Riyon1@*123", "Riyon1@*123"},
       	{"Riyon1@*123", "Riyon1@*12"},
         {"Riyon1@*123", ""},
          {"", "Riyon1@*123"},
           {"", ""}
        };
    }

	@Test(dataProvider = "forgotpassword")
	public void validateforgotPassword(String newpassword , String confirmpassword) throws InterruptedException {

		// Click forgotpassword link in login page
		driver.findElement(By.xpath("//*[text()='Forgot your password ?']")).click();

		// Entering the registered mail id
		// driver.findElement(By.name("email")).sendKeys("ramesharavindhkarthikeyan.qa@gmail.com");

		// Click submit
		// driver.findElement(By.xpath("//*[@type='submit']")).click();

		// Enter the link from the mail and navigate note:: It is a static link the current one below
		driver.navigate().to(
				"https://dev.tell.navadhiti.com/reset-password/668b7a7fa8ff7781048fbc57/77f0991ee7fed11e1a61c7769dca8f96da516db8214eea98f6b956a5519f77df");

		
		WebElement newPasswordtextfield = driver.findElement(By.name("newPassWord"));
		
		// Enter the new password
		newPasswordtextfield.sendKeys(newpassword);

		WebElement confirmPasswordtextfield = driver.findElement(By.name("confirmPassWord"));

		// Entering the confirm password
		confirmPasswordtextfield.sendKeys(confirmpassword);

		//Click on submit
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
		
		try {
			
			WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role])[2]/div[2]")));
			
	        String toastmessageverify = toastMessage.getText();
	        
	        if(toastmessageverify.equals("Password has Changed Successfully"))
	        {
	        	Assert.assertEquals(toastmessageverify, "Password has Changed Successfully");
	        }

			else if (toastmessageverify.equals("Invalid link or link expired")) {
				
				Assert.assertEquals(toastmessageverify, "Invalid link or link expired");
			}
		}
		
		catch (Exception e)
		{
			// Check newpassword  and confirmPassword field is Empty with different warnings message
			if(newPasswordtextfield.getAttribute("value").isEmpty() && confirmPasswordtextfield.getAttribute("value").isEmpty())
			{
				
				WebElement newpasswordElement =driver.findElement(By.xpath("//span[text()='Password must be strong: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number, and one special character']"));

				String newPasswordWarningmessage = newpasswordElement.getText().trim().replaceFirst("^\\*", "").trim();
			
				Assert.assertEquals(newPasswordWarningmessage,"Password must be strong: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number, and one special character");
				
				WebElement confirmpasswordElement =driver.findElement(By.xpath("//span[text()='Confirm password is required']"));
				
				String confirmPasswordWarningmessage = confirmpasswordElement.getText().trim().replaceFirst("^\\*", "").trim();

				Assert.assertEquals(confirmPasswordWarningmessage, "Confirm password is required");
			
				
			}
			
			// Check newpassword  
			else if (newPasswordtextfield.getAttribute("value").isEmpty())
			{
	        	String newPasswordWarningmessage = driver.findElement(By.xpath("//span[text()='Password must be strong: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number, and one special character']")).getText().trim().replaceFirst("^\\*", "").trim();

				Assert.assertEquals(newPasswordWarningmessage,"Password must be strong: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number, and one special character");
				
			}
	        
			//Check confirmPassword
			else if(confirmPasswordtextfield.getAttribute("value").isEmpty())
			{
				String confirmPasswordWarningmessage = driver.findElement(By.xpath("//span[text()='Passwords must match']")).getText().trim().replaceFirst("^\\*", "").trim();

				Assert.assertEquals(confirmPasswordWarningmessage, "Passwords must match");
				
			}

			
		}
		
	}


	

}
		
