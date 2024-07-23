package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import projectSpecifications.BaseClass;

public class CartPage extends BaseClass {

	@AndroidFindBy(tagName = "android.widget.TextView")
	private WebElement cartHeader;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private WebElement productPrice;

	@AndroidFindBy(uiAutomator = "//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
	private WebElement checkBoxfield;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/termsButton\"]")
	private WebElement longPressElement;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
	private WebElement close;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/btnProceed\")")
	private WebElement Proceed;

	public CartPage verifyProducts() {
		String capturetext = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();

		System.out.println(capturetext);

		return this;

	}

	public CartPage verifyTotal() throws InterruptedException {
		Thread.sleep(3000);

		List<WebElement> productprice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));

		int productnumber = productprice.size();

		System.out.println(productnumber);

		double total = 0;

		for (int i = 0; i < productnumber; i++) {
			String pricetext = productprice.get(i).getText();

			Double price = Double.parseDouble(pricetext.substring(1));

			System.out.println(price);

			total = total + price; // 280.97

		}

		String totalprice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

		Double getformattedAmount2 = getformattedAmount(totalprice);

		Thread.sleep(2000);

		System.out.println("Till this executed");

		return this;
	}

	public CartPage checkBoxfields() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
					"new UiSelector().text(\"Send me e-mails on discounts related to selected products in future\")")))
					.click();
			
			reportStep("Click on proceed checkBox", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Click on proceed checkBox is unsuccessful", "fail");
			e.printStackTrace();
		}
		return this;
	}

	public CartPage longpressfield() throws InterruptedException, IOException {

		try {
			WebElement ele = driver.findElement(
					By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/termsButton\"]"));

			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
			reportStep("longpress on the field is successful", "Pass");
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("longpress on the field is unsuccessful", "fail");
		}
		
		return this;
	}

	public Fillform proceedtoShopField() throws InterruptedException, IOException {
		Thread.sleep(2000);

		try {
			driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();

			Thread.sleep(2000);

			driver.findElement(AppiumBy
					.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/btnProceed\")"))
					.click();
			reportStep("Click on the proceed terms is successful", "pass");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("Click on the proceed terms is unsuccessful", "fail");
		}
		
		return new Fillform(driver);
	}

}
