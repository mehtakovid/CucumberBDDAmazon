package GlueCode;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass {
	public static final Logger log = getLogger(Definitions.class);
	static int i = 1;
	
	public static boolean checkWebsiteIsUpAndRunning() {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-logo']"))).isDisplayed()) {
			log.info("Website is up and Running.");
			return true;
		}else {
			log.fatal("Connection to Website failed.");
			return false;
		}
	}
	
	public static void SignInOption() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-link-accountList']")));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).click().build().perform();
		if(driver.findElements(By.xpath("//input[@type='email']")).isEmpty()) {
			action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).moveToElement(driver.findElement(By.xpath("//a[@class='nav-action-button'][@rel='nofollow']//span[text()='Sign in'][@class='nav-action-inner']"))).click().build().perform();	
			log.info("Sign in successful.");
		}
			}
	
	public static void EnterCreds(String Username, String Password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))).sendKeys(Username);
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']"))).sendKeys(Password);
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	}
	
	public void LoginVerification() {
		if(driver.findElement(By.xpath("//span[text()='Hello, Kovid']")).isDisplayed()) {
			log.info("Login Successfull.");
		}else if(driver.findElement(By.xpath("//h4[text()='There was a problem']")).isDisplayed()) {
			log.fatal("Login Failed.");
		}
	}
	
	@Before("@SSO")
	public void initializeChromeforSSO() {
		System.setProperty("webdriver.chrome.driver", ".//Drivers/chromedriver78.exe");
	    driver = new ChromeDriver();
	    wait = new WebDriverWait(driver,30);
	    checkWebsiteIsUpAndRunning();
	    SignInOption();
	    EnterCreds("kovidmehta10@gmail.com","newhoneybees@93");
	    LoginVerification();
	}
	
	@Before
	public void initializeChrome() {
		System.setProperty("webdriver.chrome.driver", ".//Drivers/chromedriver78.exe");
	    driver = new ChromeDriver();
	    wait = new WebDriverWait(driver,30);
	}
	
	@Before("@Firefox")
	public void initializeFireFox() {
		System.setProperty("webdriver.gecko.driver", ".//Drivers/geckodriver.exe");
	    driver = new ChromeDriver();
	    wait = new WebDriverWait(driver,30);
	}
	
	@Before("@Edge")
	public void initializeEdge() {
		System.setProperty("webdriver.msedge.driver", ".//Drivers/msedgedriver.exe");
	    driver = new ChromeDriver();
	    wait = new WebDriverWait(driver,30);	
	}
	
	
	@After
	public void afterScenario() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\Users\\komehta\\eclipse-workspace\\CucumberFramework\\Screenshots\\Ticker-"+i+".jpeg"));
		i++;
		driver.quit();
		driver=null;
	}
	
}
