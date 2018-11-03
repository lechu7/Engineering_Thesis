package com.engineering_work.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class REPO {

	public static String link = "http://www.nbp.pl";
	public static String linkTabelaA = "http://www.nbp.pl/home.aspx?f=/kursy/kursya.html";
	public static String linkTabelaB = "http://www.nbp.pl/home.aspx?f=/kursy/kursyb.html";
	public static String linkGold = "http://www.nbp.pl/home.aspx?c=/ascx/Zloto_cena.ascx";

	public enum Browsers {
		Firefox, Chrome, Opera, IE, Edge;
	}

	public Browsers browser;

	static By logo = new By.ByCssSelector("html body div#pageHolder form#F1 div#mainHeader div#headBanner.pl a");
	static By statisticAndReporting = new By.ById("navBarMain_item_52_cell");
	static By rates = new By.ById("navBarMain_item_65_cell");
	static By tableA = new By.ByXPath("/html/body/div[1]/form/div[4]/div[2]/div/ul[1]/blockquote[1]/li[1]/a");
	static By tableB = new By.ByXPath("/html/body/div[1]/form/div[4]/div[2]/div/ul[1]/blockquote[1]/li[2]/a");
	static By tableGold = new By.ByXPath("/html/body/div[1]/form/div[4]/div[2]/div/ul[1]/blockquote[4]/li[1]/a");

	public int returnNumberOfRows(WebDriver driver, String tableXPath) {
		return driver.findElements(By.xpath(tableXPath)).size();
	}

	public void changeToTable(WebDriver driver, Character tableName) throws InterruptedException {
		if (TestControl.browser == Browsers.IE) {
			switch (tableName) {
			case 'a':
				driver.navigate().to(REPO.linkTabelaA);
				break;
			case 'b':
				driver.navigate().to(REPO.linkTabelaB);
				break;
			case 'g':
				driver.navigate().to(REPO.linkGold);
				break;
			}
		} else {
			Thread.sleep(2000);
			if (driver.findElement(logo).getClass().getName() != "") {
				driver.findElement(logo).click();
				driver.findElement(statisticAndReporting).click();
				Thread.sleep(3000);
				if (TestControl.kindOfTests == "web") {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0,500);");
				}
				driver.findElement(rates).click();
				Thread.sleep(2000);
				switch (tableName) {
				case 'a':
					driver.findElement(tableA).click();
					break;
				case 'b':
					driver.findElement(tableB).click();
					break;
				case 'g':
					if (TestControl.kindOfTests == "web") {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("window.scrollBy(0,300)");
					}
					Thread.sleep(1000);
					driver.findElement(tableGold).click();
					break;
				}
				Thread.sleep(3000);
			} else {
				Assert.fail("Nie mozna znalezc logo na stronie glownej");
			}
		}
	}
}
