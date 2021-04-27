package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	private AndroidDriver<AndroidElement> driver;
   
    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(xpath = "//*[contains(@content-desc,'test-Item title')]")
    private List<AndroidElement> productNames;
    
    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Sauce Labs Bike Light')]")
    private List<AndroidElement> productNameText;
    
    @AndroidFindBy(xpath = "//*[contains(@content-desc,'test-ADD TO CART')]")
    private AndroidElement addToCartt;
    
	/*
	 * public String selectProduct () { String name= productNames.get(0).getText();
	 * productNames.get(0).click(); return name; }
	 */
    
    public String selectProduct (String productName) {
    	String name = null;
    	for(AndroidElement ele: productNames ) 
    	{
    		if(ele.getText().equalsIgnoreCase(productName))
    		{
    			name=ele.getText();
    			ele.click();
    			break;
    		}
    
    	}
    	return name;
    }
    
    public Boolean verifyElement(String val) {
    	Boolean state = null;
    	try {
    		state=driver.findElement(By.xpath("//*[contains(@text,"+"'"+val+"'"+")]")).isDisplayed();
    	} catch (Exception e) {
    		System.out.println("Product Title verify failed");
    	}
    	return state;
    }
    
    public void addToCart() {
    	addToCartt.click();
    }
    
    public void scrollTo(String text) {
    	driver.scrollTo(text);
    }
    
}
