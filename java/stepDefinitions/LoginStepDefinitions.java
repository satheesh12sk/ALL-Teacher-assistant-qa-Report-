package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import runner.Runner;

public class LoginStepDefinitions extends Runner {
	
	public static WebDriver driver;

	@Given("Open the brower")
	public void open_the_brower() {

		driver = new ChromeDriver();

	}

	@When("Go to url")
	public void go_to_url() {

		driver.get("https://allteacher.navadhiti.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@When("Enter the username")
	public void enter_the_username() {

		driver.findElement(By.name("email")).sendKeys("demoteacher1@gmail.com");
	}

	@When("Enter the password")
	public void enter_the_password() {

		driver.findElement(By.name("password")).sendKeys("zxcASD123@!$");
	}

	@When("Click login Button")
	public void click_login_button() {
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	@Then("Verify the login Message")
	public void verify_the_login_message() {

		String loginStatus = driver.findElement(By.xpath("//div[text()='Login successful']")).getText();
		Assert.assertEquals(loginStatus, "Login successful");

		driver.close();
	}

}
