package mainPackageTellPortal;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import data.DataDriven;

public class ProjectSpecifications {

	public WebDriver driver;
	
	public WebDriverWait wait;
	
	public int sheetAt;

	@BeforeMethod
	public void setUp() {

		driver = new ChromeDriver();

		driver.get("https://dev.tell.navadhiti.com/");

		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
