package com.engineering_work.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public abstract class TestControl extends CheckExchange {


	public WebDriver driver;


	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfEurope;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfAsia;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfAustralia;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfNorthAmerica;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfSouthAmerica;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfAfrica;
	public ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesAll;
	//Continent Name list
	public ArrayList<String> continentName;
	
	public ArrayList<ArrayList<ObjectAllAboutCurrencyCSV>> listOfListsCurrency;

	public boolean firstRun = true;
	public static REPO.Browsers browser;
	
	//Button in messagebox that pops up when selection is empty
	  public Object[] optionsOK = {"OK"};
	  int error;
	  //String with kind of tests (mobile/web)
	  String kindOfTests;
	  
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void beforeTest() throws IOException {
		//set kindOfTests on Web. Only Chrome have mobile or emulator option
		kindOfTests="web";
		
		// switch the browser
		switch (browser) {
		case Firefox:
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
			try {
				//start timer
				long start = System.nanoTime();
			driver = new FirefoxDriver();
			//stop timer
			long elapsedTime = System.nanoTime() - start;
			super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			} catch (Exception e) {
				int error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()+". \nAplikacja zostanie wy³¹czona.", "B³¹d przegl¹darki "+ browser.toString()+".",
						  JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.ERROR_MESSAGE,
		                   null,
		                   optionsOK,
		                   optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Firefox", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 74);
			break;

		case Chrome:
			if(JavaFX.webTest.isSelected()==true) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
			try {
				//start timer
				long start = System.nanoTime();
				driver = new ChromeDriver();
				//stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			} catch (Exception e) {
				error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()+". \nAplikacja zostanie wy³¹czona.", "B³¹d przegl¹darki "+ browser.toString()+".",
						  JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.ERROR_MESSAGE,
		                   null,
		                   optionsOK,
		                   optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			}
			//Physics device
			else if(JavaFX.phisicDevice.isSelected()==true){
				//set kindOfTests on mobile
				kindOfTests="mobile";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("BROWSER_NAME", "Android");
				capabilities.setCapability("VERSION", "4.4.2"); 
				capabilities.setCapability("deviceName","GT-I9195");
				capabilities.setCapability("platformName","Android");
				
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("noReset", true);
				//start timer
				long start = System.nanoTime();
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				//stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			}
			//Emulator
			else {
				//set kindOfTests on emulartor
				kindOfTests="emulator";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("BROWSER_NAME", "Android");
				capabilities.setCapability("VERSION", "8.1"); 
				capabilities.setCapability("deviceName","emulator-5554");
				capabilities.setCapability("platformName","Android");
				
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("noReset", true);
				//start timer
				long start = System.nanoTime();
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				//stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			}

			super.addToLogs("Uruchomiono Chrome", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 81);
			break;
		case Opera:
			System.setProperty("webdriver.opera.driver", ".\\src\\test\\resources\\drivers\\operadriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\Damian\\AppData\\Local\\Programs\\Opera\\launcher.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			try {
				//start timer
				long start = System.nanoTime();
			driver = new OperaDriver(capabilities);
			//stop timer
			long elapsedTime = System.nanoTime() - start;
			super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			} catch (Exception e) {
				error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()+". \nAplikacja zostanie wy³¹czona.", "B³¹d przegl¹darki "+ browser.toString()+".",
						  JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.ERROR_MESSAGE,
		                   null,
		                   optionsOK,
		                   optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Opere", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 91);
			break;
		case IE:
			System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\drivers\\IEDriverServer64.exe");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			try {
				//start timer
				long start = System.nanoTime();
			driver = new InternetExplorerDriver(caps);
			//stop timer
			long elapsedTime = System.nanoTime() - start;
			super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			} catch (Exception e) {
			 error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()+". \nAplikacja zostanie wy³¹czona.", "B³¹d przegl¹darki "+ browser.toString()+".",
						  JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.ERROR_MESSAGE,
		                   null,
		                   optionsOK,
		                   optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			this.driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Internet Explorer", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 99);
			break;
		case Edge:
			System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
			try {
				//start timer
				long start = System.nanoTime();
			driver = new EdgeDriver();
			//stop timer
			long elapsedTime = System.nanoTime() - start;
			super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			} catch (Exception e) {
			error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()+". \nAplikacja zostanie wy³¹czona.", "B³¹d przegl¹darki "+ browser.toString()+".",
						  JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.ERROR_MESSAGE,
		                   null,
		                   optionsOK,
		                   optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Microsoft Edge", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 105);
			break;
		default:
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
			try {
				//start timer
				long start = System.nanoTime();
			driver = new FirefoxDriver();
			//stop timer
			long elapsedTime = System.nanoTime() - start;
			super.saveTimeToCSV(kindOfTests,"Uruchomienie",elapsedTime); 
			} catch (Exception e) {
				int error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()+". \nAplikacja zostanie wy³¹czona.", "B³¹d przegl¹darki "+ browser.toString()+".",
						  JOptionPane.PLAIN_MESSAGE,
		                   JOptionPane.ERROR_MESSAGE,
		                   null,
		                   optionsOK,
		                   optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			super.addToLogs("***ERROR***Uruchomiono Firefox z default", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 110);
			break;
		}
	}

	@Test
	public void TestCurrency() throws IOException {
		driver.get(REPO.linkTabelaA);
		//Currency exchange rate 
		super.addToLogs();
		for (int i = 0; i < listOfListsCurrency.size(); i++) {
			//Reset progress bar
			CheckExchange.iterator=0;
			//set progress
			super.setProgressLabel(i+1, listOfListsCurrency.size()+1);
			//start timer
		long start = System.nanoTime();
		for (int j = 0; j < listOfListsCurrency.get(i).size(); j++) {
			super.CheckCurrencyExchangeRate(driver, listOfListsCurrency.get(i).get(j).Table, listOfListsCurrency.get(i).get(j).Code,
					listOfListsCurrency.get(i).get(j).CodeUnit,listOfListsCurrency.get(i).get(j).Name, listOfListsCurrency.get(i).size());
		}
		//stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests,continentName.get(i),elapsedTime);    
	
		}

	}
	@Test
	public void TestGold() throws IOException {
		driver.get(REPO.linkGold);
		
		//set progress
		super.setProgressLabel(listOfListsCurrency.size()+1, listOfListsCurrency.size()+1);
		
		//start timer
		long start = System.nanoTime();
		// Gold exchange rate
		super.CheckGoldExchangeRate(driver);
		//stop timer
		long elapsedTime = System.nanoTime() - start;
		super.saveTimeToCSV(kindOfTests,"Z³oto",elapsedTime);    
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
				super.addToLogs("***ERROR***Nie udalo sie zamknac Opery (killProcess)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 141);
			}
		}
		// Problem with closing the browser (Edge)
		if (browser == REPO.Browsers.Edge) {
			try {
				Runtime.getRuntime().exec("taskkill /f /im edge.exe");
			} catch (IOException e) {
				e.printStackTrace();
				super.addToLogs("***ERROR***Nie udalo sie zamknac Edge (killProcess)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 141);
			}
		}
		super.addToLogs("Zamknieto przegladarkê " + browser.toString() + ". ", getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 144);
	}
	//Return name of Browser for CSV file with time
	public static String returnBrowserName()
	{
		return browser.toString();
	}

}
