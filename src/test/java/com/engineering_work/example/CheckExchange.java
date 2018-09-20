package com.engineering_work.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

public class CheckExchange {
	Rest_currency rc = new Rest_currency();
	REPO repo = new REPO();
	Logs logi = Logs.getInstance();
	DecimalFormat df = new DecimalFormat("#.####");

	// Check for gold rate
	public void CheckExchangeGold(WebDriver driver) throws IOException {
		driver.navigate().to(REPO.linkGold);
		System.out.println("Change side to table gold");
		logi.addToLogs("INFO Zmiana strony na tablice ze zlotem.", getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 22);

		// Value of exchange from RestAPI
		String tmp = rc.exchange();
		Float exchangeFromRestAPI = Float.parseFloat(tmp);
		// Round value to four decimal places
		exchangeFromRestAPI = round(exchangeFromRestAPI, 2);

		// Value of exchange from WebSides
		// Replace because in webside we have ',' but in CSV we have '.'
		String exchangeFromWebSideString = driver
				.findElement(By.xpath(".//*[@id='contentholder']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]"))
				.getText().toString().replace(',', '.');
		Float exchangeFromWebSide = Float.parseFloat(exchangeFromWebSideString);

		// System.out.println(exchangeFromRestAPI);
		// System.out.println(exchangeFromWebSide);

		// System.out.println(exchangeFromRestAPI.compareTo(exchangeFromWebSide));
		AssertJUnit.assertEquals("Porownanie kursu z REST API i strony dla zlota.", exchangeFromRestAPI,
				exchangeFromWebSide);
		if (exchangeFromRestAPI.compareTo(exchangeFromWebSide) == 0) {
			logi.addToLogs("PASS- Wartoœci zgodne dla z³ota równe " + exchangeFromWebSide,
					getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(), 46);
		} else {
			logi.addToLogs(
					"***FAIL- Nie zgodna wartoœæ dla z³ota :API- " + exchangeFromRestAPI + " /WebSide- "
							+ exchangeFromWebSide,
					getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(), 51);
		}

	}

	// check for currency rate
	public void CheckExchangeCurrency(WebDriver driver, String table, String code, String codeUnit, String name)
			throws IOException {
		String side = driver.getCurrentUrl().toString();
		// Value of exchange from RestAPI
		Float exchangeFromRestAPI = Float.parseFloat(rc.exchange(table, code));
		// Multipler and round value to four decimal places
		exchangeFromRestAPI = exchangeFromRestAPI * returnMultiplier(codeUnit);
		exchangeFromRestAPI = round(exchangeFromRestAPI, 4);

		// change side to currency exchange check
		switch (table) {
		case "A":
			if (side.charAt(42) == 'b') {
				driver.navigate().to(REPO.linkTabelaA);
				System.out.println("Change side to table A");
				logi.addToLogs("INFO Zmiana strony na tablice A.", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 73);
			}
			break;
		case "B":
			if (side.charAt(42) == 'a') {
				driver.navigate().to(REPO.linkTabelaB);
				System.out.println("Change side to table B");
				logi.addToLogs("INFO Zmiana strony na tablice B.", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 81);
			}
			break;
		default:
			logi.addToLogs("***ERROR***Zle wybrano link z tabela", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 86);
			break;
		}
		// XPath to table
		String tableXPath = ".//*[@id='article']/table/tbody/tr/td/center/table[1]";
		// Count elements in table from side
		int countListOnSide = repo.returnRowsCount(driver, tableXPath + "/tbody/tr");
		// Loop through all elements from side until find element witch looking for
		// i = 2 because index of first element is equal 2
		for (int i = 2; i <= countListOnSide; i++) {
			// If "Kod waluty" from side is equel CodeUnit form CSV file
			if (driver.findElement(By.xpath(tableXPath + "/tbody/tr[" + i + "]/td[2]")).getText()
					.compareTo(codeUnit) == 0) {
				// Value of exchange from WebSides
				// Replace because in webside we have ',' but in CSV we have '.'
				String exchangeFromWebSideString = driver
						.findElement(By.xpath(tableXPath + "/tbody/tr[" + i + "]/td[3]")).getText().toString()
						.replace(',', '.');
				Float exchangeFromWebSide = Float.parseFloat(exchangeFromWebSideString);

				// System.out.println(exchangeFromRestAPI);
				// System.out.println(exchangeFromWebSide);

				// System.out.println(exchangeFromRestAPI.compareTo(exchangeFromWebSide));
				AssertJUnit.assertEquals("Porownanie kursu z REST API i strony.", exchangeFromRestAPI,
						exchangeFromWebSide);
				if (exchangeFromRestAPI.compareTo(exchangeFromWebSide) == 0) {
					logi.addToLogs(
							"PASS- Wartoœci zgodne dla " + name + "- KOD: " + code + " równe " + exchangeFromWebSide,
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							116);
				} else {
					logi.addToLogs(
							"***FAIL- Nie zgodna wartoœæ dla " + code + " :API- " + exchangeFromRestAPI + " /WebSide- "
									+ exchangeFromWebSide,
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							122);
				}
			}
		}
		// System.out.println("Tu dosz³o");
	}

	// function to return Multiplier for the Currency
	float returnMultiplier(String codeUnit) {
		String[] tmp = codeUnit.split(" ");
		return Float.parseFloat(tmp[0]);
	}

	@SuppressWarnings("deprecation")
	// function to round value from REST API to four decimal places
	public static float round(float d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}

}
