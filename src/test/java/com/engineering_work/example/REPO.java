package com.engineering_work.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class REPO {

	public static String link="http://www.nbp.pl";
	public static String linkTabelaA="http://www.nbp.pl/home.aspx?f=/kursy/kursya.html";
	public static String linkTabelaB="http://www.nbp.pl/home.aspx?f=/kursy/kursyb.html";
	
	public enum Browsers {
		Firefox, Chrome, Opera, IE, Edge;
	}
	public Browsers browser;
	
	
	
	
	
	public int returnRowsCount(WebDriver driver,String tableXPath)
	{
		return driver.findElements(By.xpath(tableXPath)).size();
	}
}
