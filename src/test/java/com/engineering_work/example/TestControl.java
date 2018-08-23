package com.engineering_work.example;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestControl {

	WebDriver driver;
	Rest_currency rc = new Rest_currency();
	Logs logi=Logs.getInstance();
	
	public enum Browsers {
		Firefox, Chrome, Opera, IE, Edge;
	}

	// toDo Lepszy wybór przegl¹darki
	Browsers browser = Browsers.Firefox;

	

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void beforeTest() throws IOException {
		//clear the file logs.txt 
		//logi.clearFileLogs();
		switch (browser) {
		case Firefox:
			 System.setProperty("webdriver.gecko.driver",".\\src\\test\\resources\\drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
			 driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Firefox",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),47);
			break;

		case Chrome:
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Chrome",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),54);
			break;
		case Opera:
			System.setProperty("webdriver.opera.driver", ".\\src\\test\\resources\\drivers\\operadriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\Damian\\AppData\\Local\\Programs\\Opera\\launcher.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			driver = new OperaDriver(capabilities);
			driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Opere",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),64);
			break;
		case IE:
			System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\drivers\\IEDriverServer64.exe");
			//DesiredCapabilities caps = new DesiredCapabilities();
			//caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			logi.addToLogs("Uruchomiono Internet Explorer" ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),72);
			break;
		case Edge:
			System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Microsoft Edge",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),78);
			break;
		default:
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			logi.addToLogs("***ERROR***Uruchomiono Firefox z default",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),83);

			break;
		}

	}

	@Test
	public void Test1() {
		
		driver.get("http://www.nbp.pl");
		 
		rc.exchange("a","CLP");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement header = driver.findElement(By.id("breadcrumbs"));
		AssertJUnit.assertTrue((header.isDisplayed()));
	}

	@AfterTest
	public void afterTest() {
		 driver.quit();
		// driver.close();

		// Problem with closing the browser (Opera)
		if (browser == Browsers.Opera) {
			try {
				Runtime.getRuntime().exec("taskkill /f /im opera.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
