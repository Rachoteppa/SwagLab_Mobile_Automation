package stepDefinations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.Scenario;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.Capabilities;
import utils.ConfigFileReader;

public class stepDefinations extends Capabilities {
	ConfigFileReader configFileReader;
	LoginPage loginPage;
	HomePage homePage;
	CheckoutPage checkoutPage;

	//private static AndroidDriver driver;
	private Scenario scenario;
	
	/*
	 * @After public void stop(){ stopServer(); }
	 */
	/*
	 * @Before public void before(Scenario s) {
	 * 
	 * Driverhandler.initReports(s.getName()); 
	 * Driverhandler.reportLog("Reports " +s.getName()); }
	 */
	@Before
	public void before(Scenario scenario) {
	    this.scenario = scenario;
	    initReports(this.scenario.getName()); 
		reportLog("Reports " +this.scenario.getName()); 
	}

	@After
	public void after() {
		reportLog("Quit browser ");
		quit();
	}
	
	@Given("launch swaglabs app")
	public void launch_swaglabs_app() throws Exception {
		preparation();
		reportLog("Successfully launched APP ");
	}

	@When("login as public_user")
	public void login_as_public_user() throws InterruptedException {
		Thread.sleep(8000);
		loginPage = new LoginPage(androidDriver);
		configFileReader= new ConfigFileReader();
		loginPage.login(configFileReader.getProperty("user.name"),configFileReader.getProperty("user.password"));
		reportLog("Logged in successfully ");
	}

	@When("add product {string} to cart")
	public void add_product_to_cart(String productname) throws InterruptedException {
		Thread.sleep(8000);
		homePage = new HomePage(androidDriver);
		homePage.selectProduct(productname);
		//assertTrue(homePage.verifyElement(expectedResult));
		homePage.scrollTo("ADD TO CART");
		homePage.addToCart();
		reportLog("Successfully added product ");
	}
	
	@When("add random product")
	public void add_random_product() throws InterruptedException {
		Thread.sleep(8000);
		reportLog("Add product ");
		homePage = new HomePage(androidDriver);
		homePage.addToCart();
		reportLog("Successfully added product ");
	}
	
	@Then("verify checkout result page")
	public void verify_checkout_result_page() throws InterruptedException {
		reportLog("Verify checkout ");
		checkoutPage=new CheckoutPage(androidDriver);
		waitTillElementDisplayed(checkoutPage.cartElement);
		checkoutPage.clickCart();
		checkoutPage.scrollTo("CHECKOUT");
		checkoutPage.clickcheckOut();
		checkoutPage.enterDetails();
		checkoutPage.scrollTo("FINISH");
		checkoutPage.clickfinish();
		assertTrue(checkoutPage.verifyElement());
		reportLog("Successfully checked out ");
	}
	
	@When("remove product from cart")
	public void remove_product_from_cart() {
		reportLog("Remove product ");
		checkoutPage=new CheckoutPage(androidDriver);
		checkoutPage.removeProductFromCart();
		reportLog("successfully removed ");
	}

	@Then("verify cart is empty")
	public void verify_cart_is_empty() {
		reportLog("Verify cart ");
		checkoutPage=new CheckoutPage(androidDriver);
		assertFalse(checkoutPage.verifyElement("1"));
		reportLog("Cart is empty ");
	}

}
