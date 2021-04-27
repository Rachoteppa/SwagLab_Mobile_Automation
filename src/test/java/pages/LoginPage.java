package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	private AndroidDriver<AndroidElement> driver;
   
    public LoginPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    private AndroidElement userNameElement;
    
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    private AndroidElement passwordElement;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
    private AndroidElement loginBtnElement;
    
    public boolean isDisplayed() {
        return loginBtnElement.isDisplayed();
    }
    
    public void typeUserName(String name) {
    	userNameElement.sendKeys(name);
    }
    
    public void typePassword(String password) {
        passwordElement.sendKeys(password);
    }
    
    public void clickLogin() {
    	loginBtnElement.click();
    }
    
    public void login (String name, String password) {
        
    	typeUserName(name);
        typePassword(password);
        clickLogin();
    }
	
	
	
}
