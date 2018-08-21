//Klasa pobierajaca z API kurs walut
package com.engineering_work.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Rest_currency {

	// ... , bool ObjectCurrency- rozpoznanie czy kurs z³ota czy waluty
	public String exchange( String table, String code, boolean Currency) {
		//adres url w stringu
		String url;
		//adres url
		URL urlTMP;
		//deklaracja po³¹czenia
		URLConnection Connection;
		BufferedReader Reader;
		String Line;
		StringBuilder sbResponse;
		String Response = null;
				
		//dla waluty
		if	(Currency==true)
			{
			url="http://api.nbp.pl/api/exchangerates/rates/"+table+"/"+code+"/?format=json";
			try {
				urlTMP = new URL(url);
				Connection = urlTMP.openConnection();
				Reader = new BufferedReader(new InputStreamReader(Connection.getInputStream(), StandardCharsets.UTF_8));
				sbResponse = new StringBuilder();
				

				while ((Line = Reader.readLine()) != null) {
					sbResponse.append(Line);
				}
				
			    Gson gson = new Gson();
				ObjectCurrency cur = gson.fromJson(sbResponse.toString(), ObjectCurrency.class);
				Response=cur.Get_currency_exchange().toString();
				
				//wypisanie na konsole wartoœci dla waluty
				String tmpConsole="Wartoœæ kursu dla "+cur.Get_currency_name().toString()+" wynosi "+cur.Get_currency_exchange().toString()+" z³otych.";
				System.out.println(tmpConsole);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		//dla z³ota
		else
		{
			url="http://api.nbp.pl/api/cenyzlota?format=json";
			try {
				urlTMP = new URL(url);
				Connection = urlTMP.openConnection();
				Reader = new BufferedReader(new InputStreamReader(Connection.getInputStream(), StandardCharsets.UTF_8));
				sbResponse = new StringBuilder();
				
				while ((Line = Reader.readLine()) != null) {
					sbResponse.append(Line);
				}

				System.out.println(sbResponse.toString());
			    Gson gson = new Gson();
			    ObjectGold gold = gson.fromJson(sbResponse.toString(), ObjectGold.class);		    
			    Response=gold.Get_gold_exchange().toString();
				
				//wypisanie na konsole wartoœci dla z³ota
				String tmpConsole="Wartoœæ kursu dla z³ota wynosi "+gold.Get_gold_exchange().toString()+" z³otych.";
				System.out.println(tmpConsole);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		
		
		return Response;
	}
}
