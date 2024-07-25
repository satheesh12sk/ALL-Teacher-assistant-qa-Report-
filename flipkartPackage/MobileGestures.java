package flipkartPackage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class MobileGestures extends FlipKart {
	
	
	public AndroidDriver driver;

	public MobileGestures(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	
	public MobileGestures swipeIntoView(String str)
	{
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true))"
			    + ".scrollIntoView(new UiSelector().text(\"India\"));"));
		return this;
	}
	
	
	public MobileGestures swipeIntoViewwithElemenet(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"direction", "down", "percent", 50.0,"speed", 5000
			));
		return this;
	}
	
	public MobileGestures swipeIntoView2(String str)// Rectify
	{
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true))"
			    + ".scrollIntoView(new UiSelector().text(\""+str+"\"));"));
		return this;
	}
	
	
	public MobileGestures longpress(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000
			));
		return this;

	}
	
	
	public MobileGestures doubliClickGestures(WebElement ele)
	{
		// Java
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) ele).getId()
		));
		return this;
		
	}
	
	public MobileGestures dragGestures(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", 100,
			    "endY", 100
			));
		return this;
	}
	
	public MobileGestures SwipeByCordinatesGestures(int startX, int startY, int endX ,int endY)
	{
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", 
			    ImmutableMap.of(
			        "startX", startX,
			        "startY", startY,
			        "endX", endX,
			        "endY", endY,
			        "direction", "down",
			        "percent", 10.0,
			        "duration", 800  // Adjust the duration as needed (in milliseconds)
			    ));
		return this;
	}
	
	public MobileGestures flingGestures(WebElement ele)
	{
		boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: flingGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "direction", "down",
			    "speed", 500
			));
		return this;
	}
	
	
	public MobileGestures pinchOpenGestures(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "percent", 0.75
			));
		return this;
	}
	
	public MobileGestures pinchCloseGestures(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "percent", 0.75
			));
		return this;
	}
	
	
	public MobileGestures scrollUntilend()
	{
		
		boolean canScrollMore;
		do
	 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 50.0
			    , "speed", 8000
			));
		
		while(canScrollMore);
		return this;
	}
	
	public MobileGestures scrollUntilUp()
	{
		
		boolean canScrollMore;
		do
	 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "up",
			    "percent", 10.0
			    , "speed", 8000
			));
		
		while(canScrollMore);
		return this;
	}
	
	  public void clickByCoordinates(AndroidDriver driver, int x, int y) {
	        // Use JavaScript to perform click at coordinates
		  JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("mobile: shell", "input tap " + x + " " + y);
	    }
	
	
	
	
	
}







