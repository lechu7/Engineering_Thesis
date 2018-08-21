package com.engineering_work.example;

import java.util.ArrayList;

//obiekt waluty z list¹ obiektów ObjectRate
public class ObjectCurrency{

	
	private String currency;
	private ArrayList<ObjectRate> objectRates = new ArrayList<ObjectRate>();

	ObjectCurrency(String table,String currency,String code, ArrayList<ObjectRate> objectRates)
	{
		this.currency=currency;
		this.objectRates=objectRates;
	}
	//Zwraca nazwê waluty
	public String Get_currency_name() {
		return this.currency;
	}
	//Zwraca aktualny kurs waluty
	public Double Get_currency_exchange() {
		return this.objectRates.get(0).Get_currency_exchange();
	}
}
