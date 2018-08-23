package com.engineering_work.example;

import java.util.ArrayList;

//currency object with object list ObjectRate 
public class ObjectCurrency {

	private String currency;
	private ArrayList<ObjectRate> rates;

	ObjectCurrency(String currency, ArrayList<ObjectRate> objectRates) {
		this.currency = currency;
		this.rates = new ArrayList<ObjectRate>();
	}

	// return currency name
	public String Get_currency_name() {
		return this.currency;
	}

	// return currency exchange from method form ObjectRate
	public Double Get_currency_exchange() {
		return this.rates.get(0).Get_currency_exchange();
	}
}
