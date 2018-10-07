//The class that downloads the current exchange rate from the API
package com.engineering_work.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public abstract class Rest_currency extends TestTimer{

	// Method for currency
	protected String exchange(String table, String code) throws IOException {
		
		
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
			// message including JSON on console 
			System.out.println(sbResponse.toString());

			Gson gson = new Gson();
			ObjectCurrency cur = gson.fromJson(sbResponse.toString(), ObjectCurrency.class);
			Response = cur.Get_currency_exchange().toString();

			// message including values for the currency on console 
			String tmpConsole = "Values for the currency " + cur.Get_currency_name().toString() + " is "
					+ Response + " zlotych.";
			System.out.println(tmpConsole);
			//adding logs
			super.addToLogs("Pobrano kurs waluty: "+code+": "+Response+ " zlotych." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),54);

		} catch (Exception e) {
			e.printStackTrace();
			//adding logs
			super.addToLogs("***ERROR***Nie pobrano kursu waluty- "+code+"." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),59);

		}
		return Response;

	}

	// Method for gold
	protected String exchange() throws IOException {
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
			// message including JSON on console 
			System.out.println(sbResponse.toString());
			Gson gson = new Gson();
			ObjectGold[] gold = gson.fromJson(sbResponse.toString(), ObjectGold[].class);
			Response = gold[0].Get_gold_exchange().toString();
			
			// message including values for the gold on console 
			String tmpConsole = "Values for the gold is " + gold[0].Get_gold_exchange() + " zlotych.";
			System.out.println(tmpConsole);
			//adding to logs
			super.addToLogs("Pobrano kurs zlota- "+gold[0].Get_gold_exchange()+ " zlotych." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),99);
		} catch (Exception e) {
			e.printStackTrace();
			//adding to logs
			super.addToLogs("***ERROR***Nie pobrano kursu zlota- "+Response+ "zlotych." ,getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),103);

		}
		return Response;
	}
}
