package com.engineering_work.example;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ObjectToTableView {
	private BooleanProperty  run;
	private StringProperty   ContinentName;
	private StringProperty   ContinentNameEnglish;
	private IntegerProperty CurrencyCountContinent;


	public ObjectToTableView(boolean run,String  ContinentName, String  ContinentNameEnglish,ArrayList<ObjectAllAboutCurrencyCSV> list) {
		this.run=new SimpleBooleanProperty(run);
		this.ContinentName = new SimpleStringProperty(ContinentName);
		this.ContinentNameEnglish = new SimpleStringProperty(ContinentNameEnglish);
		this.CurrencyCountContinent=new SimpleIntegerProperty((int)list.size());
	}
		public void SetRunProperty(Boolean setRun) { this.run=new SimpleBooleanProperty(setRun); }   
		
		public BooleanProperty runProperty() { return run; }    
	    public StringProperty ContinentNameProperty() { return ContinentName; }
	    public IntegerProperty CurrencyCountContinentProperty() { return CurrencyCountContinent; }
	    
		public Boolean returnBool() { return run.getValue(); }  
		public String returnContinentName() { return ContinentNameEnglish.getValue();}
}