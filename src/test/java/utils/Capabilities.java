package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Capabilities extends DesiredCapabilities {
	protected AndroidDriver androidDriver;
    private AppiumDriverLocalService service;
    ConfigFileReader configFileReader;
    public ExtentReports report;
	public ExtentTest Testscenario;
   
	public void preparation() throws Exception {
		configFileReader= new ConfigFileReader();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", configFileReader.getProperty("android.app.deviceName"));
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		caps.setCapability("platformName", configFileReader.getProperty("android.app.platformName"));
		caps.setCapability("platformVersion", configFileReader.getProperty("android.app.platformVersion"));
		caps.setCapability("appPackage", configFileReader.getProperty("android.app.appPackage"));
		caps.setCapability("appActivity", configFileReader.getProperty("android.app.appActivity"));
		caps.setCapability("uiautomator2ServerInstallTimeout", configFileReader.getProperty("android.app.uiautomator2ServerInstallTimeout"));
		caps.setCapability("androidInstallTimeout", configFileReader.getProperty("android.app.androidInstallTimeout"));
        //service = AppiumDriverLocalService.buildDefaultService();
        //service.start();
        //String service_url = service.getUrl().toString();
       // System.out.println("Appium Service Address: " + service_url);
        //androidDriver = new AndroidDriver(new URL(service_url), caps);
        androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(androidDriver);
    }

    
    public void stopServer() {
    	//androidDriver.close();
    	//service.stop();
    }
    
    public void waitTillElementDisplayed(MobileElement element) {
    	WebDriverWait wait = new WebDriverWait(androidDriver,20);
    	wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void initReports(String scenarioName) {
		String reportPath=System.getProperty("user.dir") + "\\reportFolder\\";
		report = ExtentManager.getInstance(reportPath);
		Testscenario = report.createTest(scenarioName);
		Testscenario.log(Status.INFO, "Starting " + scenarioName);
	}
    public void quit() {
		report.flush();
		if (androidDriver != null)
			androidDriver.quit();
	}
    
    public void reportLog(String msg) {
		Testscenario.log(Status.PASS, msg);
	}

	public void FailureLogging(String errMsg) {
		Testscenario.log(Status.FAIL, errMsg);
		ScreenshotInvoke();
	}

	public void ScreenshotInvoke() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File srcFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath + screenshotFile));
			Testscenario.log(Status.FAIL, "Screenshot-> "
					+ Testscenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath + screenshotFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
}
