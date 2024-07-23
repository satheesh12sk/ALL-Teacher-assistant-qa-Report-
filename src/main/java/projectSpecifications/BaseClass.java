package projectSpecifications;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import data.DataDriven;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pageObjects.Fillform;

public class BaseClass {

	public static AndroidDriver driver;

	public static AppiumDriverLocalService service;

	public static WebDriverWait wait;

	public static Fillform form;

	public static ExtentReports extent;

	public static ExtentTest test;

	public static String testDescription, testScenario, testCategory, testAssign;

	@BeforeSuite
	public ExtentReports startReport() {
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(
				"C:\\Users\\ADMIN\\NavaDhitiWorkSpace\\AppiumFrameWork\\ExtentReport\\ExtentReport.html");
		htmlreporter.config().setReportName("Appium Mobile Automation");
		htmlreporter.config().setDocumentTitle("Test results");
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		
		return extent;
	}

	@BeforeMethod
	public void ConfigureAppium() throws IOException {
		// code to start the server automatically by scripting the code
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\ADMIN\\NavaDhitiWorkSpace\\AppiumFrameWork\\Properties\\GlobalValues.properties");
		prop.load(file);

		service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("AppiumFile")))
				.withIPAddress(prop.getProperty("IPAddress")).withTimeout(Duration.ofSeconds(50)).usingPort(4723)
				.build();

		service.start();

		// =================================================================
		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName(prop.getProperty("DeviceName")); // Device name from the Andorid studio virtual device
		options.setPlatformVersion(prop.getProperty("DeviceVersion"));
		options.setPlatformName(prop.getProperty("Platform"));
		options.setAutomationName(prop.getProperty("Automator"));
		options.setApp(prop.getProperty("App"));

		driver = new AndroidDriver(new URL(prop.getProperty("URL")), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		form = new Fillform(driver);
	}

	@BeforeClass
	public void reportDetails() {
		test=extent.createTest(testDescription, testScenario);
		test.assignAuthor(testAssign);
		test.assignCategory(testCategory);
	}

	public Random takeScreenShot() throws IOException {
		Random ram = new Random();
		int randomNumber = ram.nextInt(5000);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./Screenshots/img"+randomNumber+".png");
		FileUtils.copyFile(src,desc);
		return ram;
	}

	public void reportStep(String msg, String status) throws IOException {
		if (status.equalsIgnoreCase("Pass")) {
			test.pass(msg,MediaEntityBuilder.createScreenCaptureFromPath(".././Screenshots/img"+takeScreenShot()+".png").build());
		} else {
			test.fail(msg,MediaEntityBuilder.createScreenCaptureFromPath(".././Screenshots/img"+takeScreenShot()+".png").build());
			throw new RuntimeException("See the report for more details");
		}

	}
	
	@DataProvider(name = "sendData")
	public String[][] fetchdata() throws IOException {
		String[][] readdata = DataDriven.readexcelData();

		return readdata;

	}

	public Double getformattedAmount(String amount) {

		Double price = Double.parseDouble(amount.substring(1));

		return price;
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

		service.stop();
	}

	@AfterSuite
	public void stopReport() {
		extent.flush();
	}

}
