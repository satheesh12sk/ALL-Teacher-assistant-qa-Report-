package testCasePackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import src.test.java.basePackage.BaseClass;
import src.test.java.pages.TF_001_LoginPage;

public class TC_002_DASHBOARD extends BaseClass{
	
	
	@BeforeTest
	public void testDetails()
	{
		excelfilename="Login";
		testName="Dashboard";
		testDescription="Validate the dashboard page";
		author="Ramesh Aravindh";
		category="Regression";
	}
	

	@Test(dataProvider ="sendData")
	public void verifyDashboard(String username, String password) throws InterruptedException
	{
	
		TF_001_LoginPage login = new TF_001_LoginPage(driver);
		
		login
		
		.username(username)
		
		.password(password)
		
		.loginButton()
		
		.verifyloginStatus()
		
		.dashboardpage()
		
		.clickOnFirstClass()
		
		.verifyClasspage();
		
	
	}
}
