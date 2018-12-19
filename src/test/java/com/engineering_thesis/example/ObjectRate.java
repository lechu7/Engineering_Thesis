//Object with Currency Rates Table for "ObjectCurrency"
package com.engineering_thesis.example;

public class ObjectRate {
	private Double mid;

	public ObjectRate(Double mid) {
		this.mid = mid;
	}

	// return currency exchange rate
	public Double Get_currency_exchange() {
		return this.mid;
	}
}
