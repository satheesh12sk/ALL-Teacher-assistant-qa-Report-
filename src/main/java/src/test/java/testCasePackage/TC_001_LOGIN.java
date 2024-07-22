package src.test.java.testCasePackage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import src.test.java.basePackage.BaseClass;
import src.test.java.pages.TF_001_LoginPage;

public class TC_001_LOGIN extends BaseClass {
	
	@BeforeTest
	public void testDetails()
	{
		excelfilename="Login";
		testName="Login";
		testDescription="Validate the login page";
		author="Ramesh Aravindh";
		category="Regression";
	}
	
	
	@Test(dataProvider ="sendData")
	public void validateloginPage(String username,String password)
	{
		TF_001_LoginPage login = new TF_001_LoginPage(driver);
		
		login
		
		.username(username)
		
		.password(password)
		
		.loginButton()
		
		.verifyloginStatus();

	}

}
