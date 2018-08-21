package com.engineering_work.example;

public class ObjectRate {
	 private Double mid;
	 public ObjectRate(String no, String effectiveDate, Double mid){
		this.mid=mid;
	}
		public Double Get_currency_exchange() {
			return this.mid;
		}
}
