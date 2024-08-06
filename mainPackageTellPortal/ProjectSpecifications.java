package mainPackageTellPortal;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ProjectSpecifications {

	public WebDriver driver;
	
	public WebDriverWait wait;
	
	public int sheetAt;
	
	public JavascriptExecutor jsExecutor;

	@BeforeMethod
	public void setUp() {
	

		driver = new ChromeDriver();

		driver.get("https://dev.tell.navadhiti.com/");

		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		jsExecutor = (JavascriptExecutor) driver;

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
