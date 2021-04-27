package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage {

	private AndroidDriver<AndroidElement> driver;
	   
    public CheckoutPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(xpath = "//*[contains(@text,'1')]")
    public AndroidElement cartElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'CHECKOUT')]")
    private AndroidElement checkOutElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'First Name')]")
    private AndroidElement firstNameElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'Last Name')]")
    private AndroidElement lastNameElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'Zip/Postal Code')]")
    private AndroidElement zipCodeElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'CONTINUE')]")
    private AndroidElement continueElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'FINISH')]")
    private AndroidElement finishElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'THANK YOU FOR YOU ORDER')]")
    private AndroidElement verifycheckoutElement;
    
    @AndroidFindBy(xpath = "//*[contains(@text,'REMOVE')]")
    private AndroidElement removeCartElement;
    
    public void clickCart() {
    	cartElement.click();
    }
    public void clickcheckOut() {
    	checkOutElement.click();
    }
    
   public void enterDetails() {
    	firstNameElement.sendKeys("rachoti");
    	lastNameElement.sendKeys("sahu");
    	zipCodeElement.sendKeys("585310");
    	continueElement.click();
   }
   
   public void scrollTo(String text) {
   	driver.scrollTo(text);
   }
   
   public void clickfinish() {
	   finishElement.click();
   }
   
   public void removeProductFromCart() {
	   removeCartElement.click();
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
   
   
   
   public Boolean verifyElement() {
   	Boolean state = null;
   	try {
   		state=verifycheckoutElement.isDisplayed();
   	} catch (Exception e) {
   		System.out.println("Product Title verify failed");
   	}
   	return state;
   }
}

