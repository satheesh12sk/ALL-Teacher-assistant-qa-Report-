package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import projectSpecifications.BaseClass;

public class MobileGestures extends BaseClass {
	
	
	public MobileGestures(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
	}
	
	public void swipeIntoView(String str)
	{
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true))"
			    + ".scrollIntoView(new UiSelector().text(\"India\"));"));
	}
	
	
	public void swipeIntoView2(String str)// Rectify
	{
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector().scrollable(true))"
			    + ".scrollIntoView(new UiSelector().text(\""+str+"\"));"));
	}
	
	
	public void longpress(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000
			));

	}
	
	
	public void doubliClickGestures(WebElement ele)
	{
		// Java
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) ele).getId()
		));
		
	}
	
	public void dragGestures(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", 100,
			    "endY", 100
			));
	}
	
	
	public void flingGestures(WebElement ele)
	{
		boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: flingGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "direction", "down",
			    "speed", 500
			));
	}
	
	
	public void pinchOpenGestures(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "percent", 0.75
			));
	}
	
	public void pinchCloseGestures(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "percent", 0.75
			));
	}
	
	
	
}







