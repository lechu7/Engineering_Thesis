//The class that download the current exchange rate with the API
package com.engineering_work.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class Rest_currency {

	// Method for currency
	public String exchange(String table, String code) throws IOException {
		Logs logi = Logs.getInstance();
		
		
		// address url in string
		String url;
		// address url
		URL urlTMP;
		// declaration of connection
		URLConnection Connection;
		BufferedReader Reader;
		String Line;
		StringBuilder sbResponse;
		String Response = null;
		

		url = "http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/?format=json";
		try {
			urlTMP = new URL(url);
			Connection = urlTMP.openConnection();
			Reader = new BufferedReader(new InputStreamReader(Connection.getInputStream(), StandardCharsets.UTF_8));
			sbResponse = new StringBuilder();

			while ((Line = Reader.readLine()) != null) {
				sbResponse.append(Line);
			}
			// writing to console JSON
			System.out.println(sbResponse.toString());

			Gson gson = new Gson();
			ObjectCurrency cur = gson.fromJson(sbResponse.toString(), ObjectCurrency.class);
			Response = cur.Get_currency_exchange().toString();

			// writing to console values for the currency
			String tmpConsole = "Values for the currency " + cur.Get_currency_name().toString() + " is "
					+ Response + " z³otych.";
			System.out.println(tmpConsole);
			//writing to logs
			logi.addToLogs("Pobrano kurs waluty- "+code+": "+Response+ " zlotych." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),54);

		} catch (Exception e) {
			e.printStackTrace();
			//writing to logs
			logi.addToLogs("***ERROR***Nie pobrano kursu waluty- "+code+"." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),59);

		}
		return Response;

	}

	// Method for gold
	public String exchange() throws IOException {
		Logs logi = Logs.getInstance();
		// address url in string
		String url;
		// address url
		URL urlTMP;
		// declaration of connection
		URLConnection Connection;
		BufferedReader Reader;
		String Line;
		StringBuilder sbResponse;
		String Response = null;

		url = "http://api.nbp.pl/api/cenyzlota?format=json";
		try {
			urlTMP = new URL(url);
			Connection = urlTMP.openConnection();
			Reader = new BufferedReader(new InputStreamReader(Connection.getInputStream(), StandardCharsets.UTF_8));
			sbResponse = new StringBuilder();

			while ((Line = Reader.readLine()) != null) {
				sbResponse.append(Line);
			}
			// writing to console JSON
			System.out.println(sbResponse.toString());
			Gson gson = new Gson();
			ObjectGold[] gold = gson.fromJson(sbResponse.toString(), ObjectGold[].class);

			// writing to console values for the gold
			String tmpConsole = "Values for the gold is " + gold[0].Get_gold_exchange() + " z³otych.";
			System.out.println(tmpConsole);
			//writing to logs
			logi.addToLogs("Pobrano kurs zlota- "+gold[0].Get_gold_exchange()+ " zlotych." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),99);
		} catch (Exception e) {
			e.printStackTrace();
			//writing to logs
			logi.addToLogs("***ERROR***Nie pobrano kursu zlota- "+Response+ "zlotych." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),103);

		}
		return Response;
	}
}
