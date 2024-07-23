package testPackage;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.Fillform;
import projectSpecifications.BaseClass;

public class TC_001_Fillform extends BaseClass{
	
//	public AndroidDriver driver;
//	
//	public TC_001_Fillform(AndroidDriver driver) {
//		
//	this.driver=driver;	
//	}
	
	@Test
	public void fillformfeature() throws IOException
	{
		Fillform form = new Fillform(driver);
		
		form.entername("Aditi Meghna");
		form.genderSelection("female");
		form.selectDropDownValues("Brazil");
		form.letShopButton();
	}

}
