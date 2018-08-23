//Object with rates table for ObjectCurrency
package com.engineering_work.example;

public class ObjectRate {
	private Double mid;

	public ObjectRate(Double mid) {
		this.mid = mid;
	}

	// return currency exchange
	public Double Get_currency_exchange() {
		return this.mid;
	}
}
