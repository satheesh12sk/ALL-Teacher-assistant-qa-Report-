package src.test.java.basePackage;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import src.test.java.data.DataDriven;

public class BaseClass {

	public static WebDriver driver;
	
	public static ExtentHtmlReporter htmlreporter;
	
	public static ExtentReports extent;
	
	public static ExtentTest test;
	
	public static String testName,testDescription, author, category;
	
	public static String excelfilename;
	
	public static WebDriverWait wait;

	
	@BeforeMethod
	public void preConditions() {

		driver = new ChromeDriver();

		driver.get("https://allteacher.navadhiti.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	@BeforeSuite
	public ExtentReports startReport()
	{
		htmlreporter = new ExtentHtmlReporter("C:\\Users\\ADMIN\\EclipseQeagleWorkSpace\\AllTeachers\\ExtentReport\\Report.html");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlreporter);
		
		return extent;
	}
	
	@BeforeClass
	public void testCaseDetails()
	{
		
		test = extent.createTest(testName,testDescription);
		test.assignAuthor(author);
		test.assignCategory(category);
	}
	
	@DataProvider (name="sendData")
	public String[][] fetchdata() throws IOException
	{
	String[][] readdata = DataDriven.readexcelData(excelfilename);
	
	return readdata;
		
	}
	public void reportStep(String msg , String status)
	{
		if(status.equalsIgnoreCase("pass"))
		{
			test.pass(msg);
		}
		else
		{
			test.fail(msg);
		}
		
	}

	
	@AfterSuite
	public void closeReport()
	{
		extent.flush();
	}
	
	

	@AfterMethod
	public void postConditions() {

		driver.close();

	}

}
