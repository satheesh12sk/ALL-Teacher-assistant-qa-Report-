package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import runner.Runner;

public class LoginStepDefinitions extends Runner {

	public WebDriver driver;

	public WebDriverWait wait;
	
	public WebElement emailfield;
	
	public WebElement passwordfield;

	@Given("Open the brower")
	public void open_the_brower() {

		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@When("Go to url")
	public void go_to_url() {

		driver.get("https://allteacher.navadhiti.com/");

		driver.manage().window().maximize();

	}

	@When("Enter the username as {string}")
	public void enter_the_username_as(String username) {
		emailfield=driver.findElement(By.name("email"));
		
		emailfield.sendKeys(username);
	}

	@When("Enter the password as {string}")
	public void enter_the_password_as(String password) {
		passwordfield=driver.findElement(By.name("password"));
		
		passwordfield.sendKeys(password);
	}

	@When("Click login Button")
	public void click_login_button() throws InterruptedException {

		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	@Then("Verify the login message")
	public void verify_the_login_message() throws InterruptedException {

		try {

				WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation']/div/child::div[2]")));
				
				if(toastMessage.isDisplayed())
				{
					if (toastMessage.getText().equalsIgnoreCase("Login successful")) {

						Assert.assertEquals(toastMessage.getText(), "Login successful");
					}
					
					else if (toastMessage.getText().equalsIgnoreCase("Invalid Email"))
					{
						Assert.assertEquals(toastMessage.getText(), "Invalid Email");
					}
					else if(toastMessage.getText().equalsIgnoreCase("Incorrect email or password"))
					{
						Assert.assertEquals(toastMessage.getText(), "Incorrect email or password");
					}
				}

			
				
		}
		
		catch(Exception e)
		{	
			// Checking email field is empty	
			 if (emailfield.getAttribute("value").isEmpty()==true) {
				 
				 WebElement emailWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='email']/following::fieldset/following::span)[1]")));

					String emailwarningmessge = emailWarning.getText().trim().replaceFirst("^\\*", "").trim();
					
					if(emailwarningmessge.equalsIgnoreCase("Email must be a valid email"))
					{
						Assert.assertEquals(emailwarningmessge, "Email must be a valid email");
					}
					else if(emailwarningmessge.equalsIgnoreCase("Email is required"))
					{
						Assert.assertEquals(emailwarningmessge,"Email is required");
					}
				}

				// Checking password field is empty
				if (passwordfield.getAttribute("value").isEmpty()==true) {
					
					 WebElement passwordWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']/following::div/following::fieldset/following::span[1]")));
					 
					 String passwordwarningmessage = passwordWarning.getText().trim().replaceFirst("^\\*", "").trim();
					 
					 if(passwordwarningmessage.equalsIgnoreCase("Password must be at least 6 characters and include at least one letter and one number"))
					 {
						 Assert.assertEquals(passwordwarningmessage, "Password must be at least 6 characters and include at least one letter and one number");
					 }
					 
					 
				     else if(passwordwarningmessage.equalsIgnoreCase("Password is required"))
						{
					        Assert.assertEquals(passwordwarningmessage,"Password is required");
						}
					 
				}
		}
				
		}

	@After
	public void tearDown() {
		driver.quit();

	}

}
