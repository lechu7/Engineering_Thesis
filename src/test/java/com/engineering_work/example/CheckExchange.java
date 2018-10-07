package com.engineering_work.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

public abstract class CheckExchange extends Rest_currency  {
	REPO repo = new REPO();
	DecimalFormat df = new DecimalFormat("#.####");
	
	//Iterator for progress bar
	protected static double iterator=0;

	// Check gold exchange rate  
	protected void CheckGoldExchangeRate(WebDriver driver) throws IOException {
		driver.navigate().to(REPO.linkGold);
		System.out.println("Change side to table of gold");
		super.addToLogs("INFO Zmiana strony na tablice ze zlotem.", getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 22);

		// Value of gold exchange rate from RestAPI
		String tmp = super.exchange();
		Float goldExchangeRateFromRestAPI = Float.parseFloat(tmp);
		// Rounding value to two decimal places
		goldExchangeRateFromRestAPI = round(goldExchangeRateFromRestAPI, 2);

		// Value of exchange rate from WebSides
		// Replacement because in webside there is ',' but in CSV there is '.'
		String goldExchangeRateFromWebSideString = driver
				.findElement(By.xpath(".//*[@id='contentholder']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]"))
				.getText().toString().replace(',', '.');
		Float goldExchangeRateFromWebSide = Float.parseFloat(goldExchangeRateFromWebSideString);

		// System.out.println(exchangeFromRestAPI);
		// System.out.println(exchangeFromWebSide);

		// System.out.println(exchangeFromRestAPI.compareTo(exchangeFromWebSide));
		AssertJUnit.assertEquals("Porownanie kursu z REST API i strony dla zlota.", goldExchangeRateFromRestAPI,
				goldExchangeRateFromWebSide);
		if (goldExchangeRateFromRestAPI.compareTo(goldExchangeRateFromWebSide) == 0) {
			super.addToLogs("PASS- Wartoœci zgodne dla z³ota równe " + goldExchangeRateFromWebSide,
					getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(), 46);
			super.changedProgress(1, 1, "Kurs z³ota: "+goldExchangeRateFromWebSide+" z³.");
		} else {
			super.addToLogs(
					"***FAIL- Nie zgodna wartoœæ dla z³ota :API- " + goldExchangeRateFromRestAPI + " /WebSide- "
							+ goldExchangeRateFromWebSide,
					getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(), 51);
		}

	}

	// check currency exchange rate
	
	protected void CheckCurrencyExchangeRate(WebDriver driver, String table, String code, String codeUnit, String name, int listCurrencySize)throws IOException, InterruptedException {
		String side = driver.getCurrentUrl().toString();
		// Value currency exchange rate from RestAPI
		Float currencyExchangeRateFromRestAPI = Float.parseFloat(super.exchange(table, code));
		// Multipler and rounding value to four decimal places
		currencyExchangeRateFromRestAPI = currencyExchangeRateFromRestAPI * returnMultiplier(codeUnit);
		currencyExchangeRateFromRestAPI = round(currencyExchangeRateFromRestAPI, 4);

		// change side depends on currency data from CSV 
		switch (table) {
		//http://-42   https://-43
		case "A":
			if (side.charAt(42) == 'b' || side.charAt(43) == 'b') {
				//driver.get(REPO.linkTabelaA);
				repo.changeToTable(driver, 'a');
				System.out.println("Change side to table A");
				super.addToLogs("INFO Zmiana strony na tablice A.", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 73);
			}
			break;
		case "B":
			if (side.charAt(42) == 'a' || side.charAt(43) == 'a') {
				//driver.get(REPO.linkTabelaB);
				repo.changeToTable(driver, 'b');
				System.out.println("Change side to table B");
				super.addToLogs("INFO Zmiana strony na tablice B.", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 81);
			}
			break;
		default:
			super.addToLogs("***ERROR***Zle wybrano link z tabela", getClass().getName().toString(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), 86);
			break;
		}
		// XPath to table
		String tableXPath = ".//*[@id='article']/table/tbody/tr/td/center/table[1]";
		// Count elements in table from side
		int countListOnWebSide = repo.returnNumberOfRows(driver, tableXPath + "/tbody/tr");
		// Loop through all elements from webside until find element which looking for
		// i = 2 because index of first element is equal 2
		for (int i = 2; i <= countListOnWebSide; i++) {
			// If "Kod waluty" from webside is equel CodeUnit form CSV file
			if (driver.findElement(By.xpath(tableXPath + "/tbody/tr[" + i + "]/td[2]")).getText()
					.compareTo(codeUnit) == 0) {
				// Value of currency exchange rate from WebSides
				// Replace because in webside there is ',' but in CSV there is '.'
				String currencyExchangeRateFromWebSideString = driver
						.findElement(By.xpath(tableXPath + "/tbody/tr[" + i + "]/td[3]")).getText().toString()
						.replace(',', '.');
				Float currencyExchangeRateFromWebSide = Float.parseFloat(currencyExchangeRateFromWebSideString);

				// System.out.println(exchangeFromRestAPI);
				// System.out.println(exchangeFromWebSide);

				// System.out.println(exchangeFromRestAPI.compareTo(exchangeFromWebSide));
				AssertJUnit.assertEquals("Porownanie kursu z REST API i strony.", currencyExchangeRateFromRestAPI,
						currencyExchangeRateFromWebSide);
				if (currencyExchangeRateFromRestAPI.compareTo(currencyExchangeRateFromWebSide) == 0) {
					super.addToLogs(
							"PASS- Wartoœci zgodne dla " + name + "- KOD: " + code + " równe " + currencyExchangeRateFromWebSide,
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							116);
							iterator++;
							super.changedProgress(iterator, listCurrencySize, "Kurs waluty "+name+": "+currencyExchangeRateFromWebSide+" z³.");
				} else {
					super.addToLogs(
							"***FAIL- Nie zgodna wartoœæ dla " + code + " :API- " + currencyExchangeRateFromRestAPI + " /WebSide- "
									+ currencyExchangeRateFromWebSide,
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							122);
				}
				return;
			}
		}
	}

	// function which returns Multiplier for the Currency
	float returnMultiplier(String codeUnit) {
		String[] tmp = codeUnit.split(" ");
		return Float.parseFloat(tmp[0]);
	}

	@SuppressWarnings("deprecation")
	// function which rounds value from REST API to four decimal places
	protected static float round(float d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}

}
