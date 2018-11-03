package com.engineering_work.example;

import java.awt.Desktop;
import java.io.File;
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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class TestControl extends CheckExchange {

	protected WebDriver driver;

	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfEurope;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfAsia;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfAustralia;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfNorthAmerica;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfSouthAmerica;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesOfAfrica;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CurrenciesAll;
	// Continent Name list
	protected static ArrayList<String> continentName;

	protected static ArrayList<ArrayList<ObjectAllAboutCurrencyCSV>> listOfListsCurrency;

	protected static REPO.Browsers browser;

	public static ExtentReports extent;
	public static ExtentTest logger;

	// Button in messagebox that pops up when selection is empty
	protected Object[] optionsOK = { "OK" };
	protected Object[] optionsYesNo = { "Tak", "Nie" };
	int error;
	// String with kind of tests (mobile/web)
	public static String kindOfTests;

	@SuppressWarnings("deprecation")
	@BeforeTest
	protected void beforeTest() throws IOException {
		extent = new ExtentReports(System.getProperty("user.dir") + "\\test-output\\STMExtentReport.html", true);
		logger = extent.startTest("Uruchomienie przegl¹darki - "+browser.toString());
		// set kindOfTests on Web. Only Chrome have mobile or emulator option
		kindOfTests = "web";

		// switch the browser
		switch (browser) {
		case Firefox:
			extent.addSystemInfo("Host Name", "PC");
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
			try {
				// start timer
				long start = System.nanoTime();
				driver = new FirefoxDriver();
				// stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
			} catch (Exception e) {
				int error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()
								+ ". \nAplikacja zostanie wy³¹czona.",
						"B³¹d przegl¹darki " + browser.toString() + ".", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE, null, optionsOK, optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Firefox", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 74);
			break;

		case Chrome:
			if (JavaFX.webTest.isSelected() == true) {
				extent.addSystemInfo("Host Name", "PC");
				System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
				try {
					// start timer
					long start = System.nanoTime();
					driver = new ChromeDriver();
					// stop timer
					long elapsedTime = System.nanoTime() - start;
					super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
				} catch (Exception e) {
					error = JOptionPane.showOptionDialog(null,
							"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()
									+ ". \nAplikacja zostanie wy³¹czona.",
							"B³¹d przegl¹darki " + browser.toString() + ".", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, optionsOK, optionsOK[0]);
					if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
						System.exit(0);
					}
				}
				driver.manage().window().maximize();
			}
			// Physics device
			else {
				extent.addSystemInfo("Host Name", "Samsung S4 mini");
				// set kindOfTests on mobile
				kindOfTests = "mobile";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("BROWSER_NAME", "Android");
				capabilities.setCapability("VERSION", "4.4.2");
				capabilities.setCapability("deviceName", "GT-I9195");
				capabilities.setCapability("platformName", "Android");

				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("noReset", true);
				// start timer
				long start = System.nanoTime();
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				// stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
			}

			super.addToLogs("Uruchomiono Chrome", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 81);
			break;
		case Opera:
			extent.addSystemInfo("Host Name", "PC");
			System.setProperty("webdriver.opera.driver", ".\\src\\test\\resources\\drivers\\operadriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\Damian\\AppData\\Local\\Programs\\Opera\\launcher.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			try {
				// start timer
				long start = System.nanoTime();
				driver = new OperaDriver(capabilities);
				// stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
			} catch (Exception e) {
				error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()
								+ ". \nAplikacja zostanie wy³¹czona.",
						"B³¹d przegl¹darki " + browser.toString() + ".", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE, null, optionsOK, optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Opere", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 91);
			break;
		case IE:
			extent.addSystemInfo("Host Name", "PC");
			System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\drivers\\IEDriverServer64.exe");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			try {
				// start timer
				long start = System.nanoTime();
				driver = new InternetExplorerDriver(caps);
				// stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
			} catch (Exception e) {
				error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()
								+ ". \nAplikacja zostanie wy³¹czona.",
						"B³¹d przegl¹darki " + browser.toString() + ".", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE, null, optionsOK, optionsOK[0]);
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
				// start timer
				long start = System.nanoTime();
				driver = new EdgeDriver();
				// stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
			} catch (Exception e) {
				error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()
								+ ". \nAplikacja zostanie wy³¹czona.",
						"B³¹d przegl¹darki " + browser.toString() + ".", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE, null, optionsOK, optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			driver.manage().window().maximize();
			super.addToLogs("Uruchomiono Microsoft Edge", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 105);
			break;
		default:
			extent.addSystemInfo("Host Name", "PC");
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\drivers\\geckodriver.exe");
			try {
				// start timer
				long start = System.nanoTime();
				driver = new FirefoxDriver();
				// stop timer
				long elapsedTime = System.nanoTime() - start;
				super.saveTimeToCSV(kindOfTests, "Uruchomienie", elapsedTime);
			} catch (Exception e) {
				int error = JOptionPane.showOptionDialog(null,
						"Najprawdopodobnie nie zainstalowano wybranej przegl¹darki: " + browser.toString()
								+ ". \nAplikacja zostanie wy³¹czona.",
						"B³¹d przegl¹darki " + browser.toString() + ".", JOptionPane.PLAIN_MESSAGE,
						JOptionPane.ERROR_MESSAGE, null, optionsOK, optionsOK[0]);
				if (error == JOptionPane.OK_OPTION || error == JOptionPane.CLOSED_OPTION) {
					System.exit(0);
				}
			}
			super.addToLogs("***ERROR***Uruchomiono Firefox z default", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 110);
			break;
		}
		extent.addSystemInfo("User Name", "Damian");
		extent.addSystemInfo("Browser", browser.toString());
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

	@Test
	protected void TestCurrency() throws IOException, InterruptedException {
		driver.get(REPO.link);
		// driver.get(REPO.linkTabelaA);
		repo.changeToTable(driver, 'a');
		// Currency exchange rate
		super.addToLogs();
		for (int i = 0; i < listOfListsCurrency.size(); i++) {
			// Reset progress bar
			CheckExchange.iterator = 0;
			// set progress
			super.setProgressLabel(i + 1, listOfListsCurrency.size() + 1);
			// start timer
			long start = System.nanoTime();
			// Report
			logger = extent.startTest("Kontynent: " + continentName.get(i));
			for (int j = 0; j < listOfListsCurrency.get(i).size(); j++) {
				super.CheckCurrencyExchangeRate(driver, listOfListsCurrency.get(i).get(j).Table,
						listOfListsCurrency.get(i).get(j).Code, listOfListsCurrency.get(i).get(j).CodeUnit,
						listOfListsCurrency.get(i).get(j).Name, listOfListsCurrency.get(i).size());
			}
			// stop timer
			long elapsedTime = System.nanoTime() - start;
			super.saveTimeToCSV(kindOfTests, continentName.get(i), elapsedTime);
		}

	}


	@Test
	protected void TestGold() throws IOException, InterruptedException {
		driver.get(REPO.link);
		// driver.get(REPO.linkGold);
		repo.changeToTable(driver, 'g');

		// set progress
		super.setProgressLabel(listOfListsCurrency.size() + 1, listOfListsCurrency.size() + 1);

		// start timer
		long start = System.nanoTime();
		// Report
		logger = extent.startTest("Kurs zlota");
		// Gold exchange rate
		super.CheckGoldExchangeRate(driver);
		// stop timer
		long elapsedTime = System.nanoTime() - start;
		super.saveTimeToCSV(kindOfTests, "Z³oto", elapsedTime);
		
	}

	@AfterTest
	protected void afterTest() throws IOException {
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
		
		
		extent.endTest(logger);
		extent.flush();
		super.addToLogs("Zamknieto przegladarkê " + browser.toString() + ". ", getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 144);


	}

	// Return name of Browser for CSV file with time
	protected static String returnBrowserName() {
		return browser.toString();
	}

}
