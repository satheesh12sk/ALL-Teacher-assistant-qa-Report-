package flipkartPackage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class FlipKartWithOutUsingfilter {

	public AppiumDriverLocalService localservices;

	@Test
	public void flipKartAutomation() throws MalformedURLException, InterruptedException {

		// Starting the Appium Server=====================================================================

		AppiumServiceBuilder service = new AppiumServiceBuilder();

		service.withAppiumJS(
				new File("C:\\Users\\ADMIN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").withTimeout(Duration.ofSeconds(20)).usingPort(4723).build();

		localservices = AppiumDriverLocalService.buildService(service);

		localservices.start();

		UiAutomator2Options capabilities = new UiAutomator2Options();

		capabilities.setDeviceName("Redmi");

		capabilities.setPlatformName("Android");

		capabilities.setPlatformVersion("10.0");

		capabilities.setAutomationName("UiAutomator2");

		capabilities.setApp(
				"C:\\Users\\ADMIN\\NavaDhitiWorkSpace\\AppiumFlipKart\\src\\test\\java\\utils\\com.flipkart.android_8.4.apk");

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(3000);

		// Selecting the language=====================================================================
		
		driver.findElement(By.xpath(
				"//android.widget.TextView[@resource-id=\"com.flipkart.android:id/tv_subtext\" and @text=\"English\"]"))
				.click();

		driver.findElement(By.xpath("//android.widget.Button[@text='CONTINUE']")).click();

		Thread.sleep(3000);

		// Log in Phone number=====================================================================
		WebElement phoneNumber = driver
				.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Phone Number\"]"));

		phoneNumber.click();

		String number = "9384739065";
		char[] charArray = number.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];

			String characterconvertedtoString = String.valueOf(c);

			int stringconvertedtoInt = Integer.parseInt(characterconvertedtoString);

			driver.pressKey(new KeyEvent(AndroidKey.valueOf("NUMPAD_" + stringconvertedtoInt)));
		}

		driver.hideKeyboard();

		Thread.sleep(3000);

		// Click on searchBox to appear the text field=====================================================================

		WebDriverWait waitforSearchBar = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement searchBar = driver.findElement(By.xpath("//android.widget.TextView[@text=\"mobiles\"]"));

		waitforSearchBar.until(ExpectedConditions.elementToBeClickable(searchBar)).click();

		// Text field=====================================================================

		WebElement searchBarInput = driver.findElement(By.className("android.widget.EditText"));

		searchBarInput.sendKeys("iphone 15 plus");

		driver.pressKey(new KeyEvent(AndroidKey.ENTER));

		driver.findElement(By.id("com.flipkart.android:id/allow_button")).click();

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\")"))
				.click();

		Thread.sleep(3000);

		// Click on first visible product=====================================================================

		WebElement firstProduct = driver
				.findElement(By.xpath("//android.widget.TextView[@text=\"Apple iPhone 15 Plus (Blue, 128 GB)\"]"));

		firstProduct.click();

		// Click on Cart=====================================================================

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Add to cart\")")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Go to cart\"]")).click();

		WebElement placeOrder = driver
				.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Place order \"]"));

		placeOrder.click();

		Thread.sleep(2000);
		
		String number2 = "9384739065";
		
		char[] charArray2 = number.toCharArray();

		for (int i = 0; i < charArray2.length; i++) {
			char c = charArray2[i];

			String characterconvertedtoString = String.valueOf(c);

			int stringconvertedtoInt = Integer.parseInt(characterconvertedtoString);

			driver.pressKey(new KeyEvent(AndroidKey.valueOf("NUMPAD_" + stringconvertedtoInt)));
		}

		driver.findElement(By.id("com.flipkart.android:id/button")).click();

		// Continue on Shopping=====================================================================

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"Continue \"]")).click();

		Thread.sleep(5000);

		System.out.println("After this page is moved to Payment gateway");

		driver.quit();
	}

}
