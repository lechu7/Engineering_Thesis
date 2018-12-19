package com.engineering_thesis.example;

import java.util.ArrayList;

//object of currency with list "ObjectRate" 
public class ObjectCurrency {

	private String currency;
	private ArrayList<ObjectRate> rates;

	ObjectCurrency(String currency, ArrayList<ObjectRate> objectRates) {
		this.currency = currency;
		this.rates = new ArrayList<ObjectRate>();
	}

	// return name of currency 
	public String Get_currency_name() {
		return this.currency;
	}

	// return currency exchange rate form "ObjectRate"
	public Double Get_currency_exchange() {
		return this.rates.get(0).Get_currency_exchange();
	}
}
