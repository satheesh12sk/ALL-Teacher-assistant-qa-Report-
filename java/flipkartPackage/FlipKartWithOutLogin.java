package flipkartPackage;

import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class FlipKartWithOutLogin {

	public AppiumDriverLocalService localservices;

	public AppiumServiceBuilder service;

	public UiAutomator2Options capabilities;

	public AndroidDriver driver;

	public ChangeAddress change;

	public WebDriverWait wait;

	public MobileGestures gest;

	@BeforeTest
	public void startService() throws MalformedURLException, URISyntaxException {
		// Starting the Appium
		// Server

		service = new AppiumServiceBuilder();

		service.withAppiumJS(
				new File("C:\\Users\\ADMIN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").withTimeout(Duration.ofSeconds(20)).usingPort(4723).build();

		localservices = AppiumDriverLocalService.buildService(service);

		localservices.start();

		// Setting the
		// capabilities

		capabilities = new UiAutomator2Options();

		capabilities.setDeviceName("Redmi");

		capabilities.setPlatformName("Android");

		capabilities.setPlatformVersion("10.0");

		capabilities.setAutomationName("UiAutomator2");

		capabilities.setAppPackage("com.flipkart.android");

		capabilities.setAppActivity("com.flipkart.android.activity.HomeFragmentHolderActivity");

		// capabilities.setCapability("adbExecTimeout", "20000");

		capabilities.setNoReset(true);

		// capabilities.setApp(
		// "C:\\Users\\ADMIN\\NavaDhitiWorkSpace\\AppiumFlipKart\\src\\test\\java\\utils\\com.flipkart.android_8.0.apk");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), capabilities);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test
	public void flipKartAutomation() throws InterruptedException {

		gest = new MobileGestures(driver);

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Account\"]")).click();

		Thread.sleep(5000);

		String saveaddress = "Saved Addresses";

		gest.swipeIntoView2(saveaddress);

		WebElement savedAddressElement = driver
				.findElement(By.xpath("//android.widget.TextView[@text=\"Saved Addresses\"]"));

		savedAddressElement.click();

		Thread.sleep(3000);

		gest.clickOnCoordinates(153, 237);

		Thread.sleep(5000);

		if(driver.findElement(By.xpath("//android.widget.Button[@text=\"Yes, Continue\"]")).isDisplayed())
		{
			driver.findElement(By.xpath("//android.widget.Button[@text=\"Yes, Continue\"]")).click();
			
		}
		else  
		{
			driver.findElement(By.xpath("//android.widget.Button[@text=\"CONFIRM\"]")).click();
	}
			
		//driver.findElement(By.xpath("//android.widget.Button[@text=\"CONFIRM\"]")).click();
		

		WebElement name = driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@resource-id=\"com.flipkart.android:id/payments_root_view_id\"]/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.EditText"));

		name.sendKeys("Ramesh Aravindh");

		driver.hideKeyboard();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement phoneNumber2 = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.widget.TextView[@text=\"Phone number (Required)\"]")));

		phoneNumber2.click();

		Thread.sleep(2000);

		List<Point> points = new ArrayList<Point>();
		points.add(new Point(457, 1334));
		points.add(new Point(426, 1057));
		points.add(new Point(299, 1327));
		points.add(new Point(278, 1163));
		points.add(new Point(278, 1163));
		points.add(new Point(441, 1189));
		points.add(new Point(113, 1053));
		points.add(new Point(113, 1053));
		points.add(new Point(248, 1452));
		points.add(new Point(102, 1152));

		gest.clickOnCoordinates(points);

		driver.hideKeyboard();

		WebElement house = driver
				.findElement(By.xpath("//android.widget.TextView[@text=\"House No., Building Name (Required)\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(house)).click();
		Thread.sleep(1000);

		String buildname = "annai Illam";

		gest.sendStringToTextField(buildname);

		driver.hideKeyboard();

		WebElement building = driver
				.findElement(By.xpath("//android.widget.TextView[@text=\"Road name, Area, Colony (Required)\"]"));
		building.click();

		gest.sendIntToTextField(12);

		driver.hideKeyboard();

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Save Address\")")).click();

		Thread.sleep(5000);

		driver.findElement(AppiumBy.accessibilityId("Back Button")).click();

		// Click on Home
		// icon

		WebElement home = driver
				.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Home\"]/android.widget.ImageView"));

		home.click();

		Thread.sleep(5000);
		WebElement searchBar = driver.findElement(By.xpath("//android.widget.TextView[@text=\"mobiles\"]"));

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(searchBar)).click();

		// Text
		// field

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBarInput = driver.findElement(By.className("android.widget.EditText"));

		wait.until(ExpectedConditions.visibilityOf(searchBarInput)).sendKeys("iphone 15 plus 256 gb");

		driver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.ENTER));

		WebElement filter = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Filter\"]"));

		filter.click();

		Thread.sleep(3000);

		MobileGestures gesture = new MobileGestures(driver);

		gesture.clickOnCoordinates(501, 220);

		gesture.scrollUntilend();

		gesture.clickOnCoordinates(110, 1112);

		Thread.sleep(5000);
		
		gesture.clickOnCoordinates(450, 317);

		Thread.sleep(3000);

		gesture.clickOnCoordinates(545, 1444);

		Thread.sleep(5000);

		// Click on first visible
		// product

		List<WebElement> productlist = driver.findElements(By.className("android.view.ViewGroup"));

		productlist.get(1).click();

		// Click on
		// Cart
		
		Thread.sleep(3000);

		gest.clickOnCoordinates(98, 1440);

		Thread.sleep(15000);

		//driver.findElement(By.xpath("//android.widget.TextView[@text=\"Go to cart\"]")).click();

		gest.clickOnCoordinates(535, 1350);

		// Continue on
		// Shopping

		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Continue \"]")).click();
		
		Thread.sleep(3000);

		driver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter your email\"]"))
				.sendKeys("ramesharavindhkarthikeyan.qa@gmail.com");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Save \"]")).click();
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		localservices.stop();

	}

}
