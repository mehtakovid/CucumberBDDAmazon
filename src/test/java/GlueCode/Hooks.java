package GlueCode;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass {
	
	static int i = 1;
	
	@Before
	public void initializeChrome() {
		System.setProperty("webdriver.chrome.driver", ".//Drivers/chromedriver.exe");
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
		FileUtils.copyFile(scrFile, new File("YourScreenshotPathGoesHere"+i+".jpeg"));
		i++;
		driver.quit();
		driver=null;
	}
	
}
