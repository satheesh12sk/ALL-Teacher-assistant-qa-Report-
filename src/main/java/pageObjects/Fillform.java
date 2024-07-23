package pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import projectSpecifications.BaseClass;

public class Fillform extends BaseClass {

	public AndroidDriver driver;

	public Fillform(AndroidDriver driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
	private WebElement namefield;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")")
	private WebElement female;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioMale\")")
	private WebElement male;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.androidsample.generalstore:id/btnLetsShop\")")
	private WebElement letsShop;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement country;

	@AndroidFindBy(id = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"India\"]")
	private WebElement countryname;

	public Fillform entername(String name) throws IOException {
		try {
			namefield.sendKeys(name);
			reportStep("Entering the username", "Pass");
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			reportStep("Entering name unsuccessful", "Fail");
			e.printStackTrace();
		}
		
		return this;
	}

	public Fillform genderSelection(String str) throws IOException {
		try {
			if (str.equalsIgnoreCase("female")) {
				female.click();
				reportStep("Clicking the female is successfull", "Pass");
			}

			else
			{
				boolean selected = male.isSelected();	
				if(selected==true)
				{	
				}	
				else
				{
					reportStep("Clicking the male is successfull", "Pass");
					male.click();	
				}	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Clicking the female is uncessful", "Fail");
			e.printStackTrace();
		}
		
		return this;
	}

	public Fillform selectDropDownValues(String name1) throws IOException {
		try {
			country.click();
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().text(\"" + name1 + "\"));"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + name1 + "\"]")))
					.click();
			reportStep("Selecting the country is successful", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Selecting the country is unsuccessful", "fail");
			e.printStackTrace();
		}
		return this;

	}

	public ProductPage letShopButton() throws IOException {
		try {
			letsShop.click();
			reportStep("click on the letshop successful", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("click on the letshop unsuccessful", "fail");
			e.printStackTrace();
		}
		return new ProductPage(driver);
	}

}
