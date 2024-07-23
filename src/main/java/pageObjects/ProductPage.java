package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import projectSpecifications.BaseClass;

public class ProductPage  extends BaseClass{
	
	
	public ProductPage(AndroidDriver driver) {
		
		BaseClass.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath = "//*[text()='Products']")
	private WebElement products;
	
	@AndroidFindBy(xpath = "com.androidsample.generalstore:id/productAddCart")
	private WebElement productclickOnAddtoCart;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]")
	private WebElement CartIcon;
	
	
	public ProductPage landingProductPage() throws InterruptedException
	{
		Thread.sleep(2000);
		String productheader = products.getText();
		Assert.assertEquals(productheader,"Products");
		return this;
	}
	
	public ProductPage addingproduct(int num) throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		try {
			driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(num).click();
			reportStep("Click on the AddtoCart on product", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reportStep("Click on the AddtoCart on product is fail", "fail");
			e.printStackTrace();
		}

		return this;
	}
	
	public CartPage clickAddtoCart()
	{
		CartIcon.click();
		
		return new CartPage();
	}

	
}
