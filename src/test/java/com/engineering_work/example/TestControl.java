package com.engineering_work.example;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestControl {
	
	//Singleton for class TestControl 
	private static TestControl instance = null;
	 public static TestControl getInstance() {
	      if(instance == null) {
	         instance = new TestControl();
	      }
	      return instance;
	   }
	
	public WebDriver driver;
	Rest_currency rc = new Rest_currency();
	CheckExchange chr= new CheckExchange();
	Logs logi=Logs.getInstance();
	
	
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyOfEurope;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyOfAsia;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyOfAustralia;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyOfNorthAmerica;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyOfSouthAmerica;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyOfAfrica;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencyAll;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrencySelected;
	
	
	
	public boolean firstRun=true;
	REPO.Browsers browser;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void beforeTest() throws IOException {

	
		
		//Mo¿na zrobiæ ¿e dopiero po wyborze z GUI tworzy siê odpoweidnia lista (do przemyœlenia)
		//Preparation CSV data file and countries list
		
		
		
		//switch the browser
		switch (browser) {
		case Firefox:
			 System.setProperty("webdriver.gecko.driver",".\\src\\test\\resources\\drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
			 driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Firefox",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),74);
			break;

		case Chrome:
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			logi.addToLogs("Uruchomiono Chrome",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),81);
			break;
		case Opera:
			System.setProperty("webdriver.opera.driver", ".\\src\\test\\resources\\drivers\\operadriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\Damian\\AppData\\Local\\Programs\\Opera\\launcher.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			driver = new OperaDriver(capabilities);
			driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Opere",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),91);
			break;
		case IE:
			System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\drivers\\IEDriverServer64.exe");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(caps);
			this.driver.manage().window().maximize();
			logi.addToLogs("Uruchomiono Internet Explorer" ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),99);
			break;
		case Edge:
			System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			 logi.addToLogs("Uruchomiono Microsoft Edge",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),105);
			break;
		default:
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			logi.addToLogs("***ERROR***Uruchomiono Firefox z default",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),110);
			break;
		}
	}

	@Test
	public void TestWebCurrency() throws IOException {
		driver.get(REPO.linkTabelaA);
		//Exchange Currency
		logi.addToLogs();
		for(int i=0 ; i<CurrencySelected.size() ; i++){
			chr.CheckExchangeCurrency(driver, CurrencySelected.get(i).Table, CurrencySelected.get(i).Code,CurrencySelected.get(i).CodeUnit, CurrencySelected.get(i).Name,CurrencySelected.size());
			}
		logi.addToLogs();
		
		//ExchangeGold
		chr.CheckExchangeGold(driver);
	}
	@Test
	public void TestWebGold() throws IOException {
		driver.get(REPO.linkGold);
		//ExchangeGold
		chr.CheckExchangeGold(driver);
	}
	@Test
	public void TestMobileCurrency() throws IOException {
		/*driver.get(REPO.linkTabelaA);
		//Exchange Currency
		ArrayList<ObjectAllAboutCurrencyCSV> tmpList=CountriesOfAfrica;
		logi.addToLogs();
		for(int i=0 ; i<tmpList.size() ; i++){
			chr.CheckExchangeCurrency(driver, tmpList.get(i).Table, tmpList.get(i).Code,tmpList.get(i).CodeUnit, tmpList.get(i).Name);
			}
		logi.addToLogs();
		
		//ExchangeGold
		chr.CheckExchangeGold(driver);
		logi.addToLogs();*/
	}
	@Test
	public void TestMobileGold() throws IOException {
		/*driver.get(REPO.linkTabelaA);
		//Exchange Currency
		ArrayList<ObjectAllAboutCurrencyCSV> tmpList=CountriesOfAfrica;
		logi.addToLogs();
		for(int i=0 ; i<tmpList.size() ; i++){
			chr.CheckExchangeCurrency(driver, tmpList.get(i).Table, tmpList.get(i).Code,tmpList.get(i).CodeUnit, tmpList.get(i).Name);
			}
		logi.addToLogs();
		
		//ExchangeGold
		chr.CheckExchangeGold(driver);
		logi.addToLogs();*/
	}
	@AfterTest
	public void afterTest() throws IOException {
		 driver.quit();
		// Problem with closing the browser (Opera)
		if (browser == REPO.Browsers.Opera) {
			try {
				Runtime.getRuntime().exec("taskkill /f /im opera.exe");
			} catch (IOException e) {
				e.printStackTrace();
				logi.addToLogs("***ERROR***Nie udalo sie zamknac Opery (killProcess)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),141);
			}
		}
		if (browser == REPO.Browsers.Edge) {
			try {
				Runtime.getRuntime().exec("taskkill /f /im edge.exe");
			} catch (IOException e) {
				e.printStackTrace();
				logi.addToLogs("***ERROR***Nie udalo sie zamknac Edge (killProcess)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),141);
			}
		}
		logi.addToLogs("Zamknieto przegladarkê "+browser.toString()+". ",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),144);
	}

}
