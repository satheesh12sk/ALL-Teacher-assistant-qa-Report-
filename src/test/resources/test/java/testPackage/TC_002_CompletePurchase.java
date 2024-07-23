package testPackage;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import projectSpecifications.BaseClass;

public class TC_002_CompletePurchase extends BaseClass {
	
	
	@BeforeTest
	public void testDetails()
	{
		testDescription="Validating the Ecommerce Website";
		testScenario="Shipping functioality";
		testAssign="RameshAravindh";
		testCategory="Regression";
	}
	
	@Test(dataProvider = "sendData")
	public void completePurchase(String username , String country , String gender) throws InterruptedException, IOException
	{
		 form.entername(username)
		.selectDropDownValues(country)
		.genderSelection(gender)
		.letShopButton()
		.addingproduct(0)
		.addingproduct(1)
		.clickAddtoCart()
		.verifyTotal()
		.checkBoxfields()
		.longpressfield()
		.proceedtoShopField();
		
		
	}

}
