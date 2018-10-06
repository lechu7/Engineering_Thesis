package com.engineering_work.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

//New thread
// Analysis elements after click start, disable elements after click, 
//enable elements after testing
public class Analysis extends JavaFX implements Runnable  {

	
	protected String web_mobile;
	protected String browserName;
	
	boolean isSelectedGold = false;
	boolean isSelectedCurrency = false;
    //TestTimer test
	Timer timer1 = new Timer();

	public void run() {
		try {
			isSelectedGold = false;
			isSelectedCurrency = false;	
			// check continents which are marked
			for (ObjectToTableView item : JavaFX.list) {
				if (item.returnBool() == true) {
					isSelectedCurrency = true;
					if (item.returnContinentName() == "Africa") {
						super.listOfListsCurrency.add(super.CurrenciesOfAfrica);
						super.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "SouthAmerica") {
						super.listOfListsCurrency.add(super.CurrenciesOfSouthAmerica);
						super.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "NorthAmerica") {
						super.listOfListsCurrency.add(super.CurrenciesOfNorthAmerica);
						super.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "Australia") {
						super.listOfListsCurrency.add(super.CurrenciesOfAustralia);
						super.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "Asia") {
						super.listOfListsCurrency.add(super.CurrenciesOfAsia);
						super.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "Europe") {
						super.listOfListsCurrency.add(super.CurrenciesOfEurope);
						super.continentName.add(item.returnContinentPolishName());
					}
				}
			}
			if (JavaFX.goldTest.isSelected() == true) {
				isSelectedGold = true;
				super.continentName.add("Z³oto");
			}
			// Select browser
			if (isSelectedCurrency == true || isSelectedGold == true) {
				if (JavaFX.Chrome.isSelected()) {
						super.browser = REPO.Browsers.Chrome;
						super.addToLogs("Wybrano Chrome (UserGUI)", getClass().getName().toString(),
								Thread.currentThread().getStackTrace()[1].getMethodName(), 21);
				
				} else if (JavaFX.Opera.isSelected()) {
					super.browser = REPO.Browsers.Opera;
					super.addToLogs("Wybrano Opera (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 25);
				} else if (JavaFX.IE.isSelected()) {
					super.browser = REPO.Browsers.IE;
					super.addToLogs("Wybrano Internet Explorer (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 29);
				} else if (JavaFX.Edge.isSelected()) {
					super.browser = REPO.Browsers.Edge;
					super.addToLogs("Wybrano Edge (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 33);
				} else {
					super.browser = REPO.Browsers.Firefox;
					super.addToLogs("Wybrano Mozilla Firefox (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 37);
				}

				// Disable all elements on the screen after click start
				disable();

				super.beforeTest();

				super.addToLogs();
				// choice between Web and Mobile test
				if (JavaFX.webTest.isSelected()==true) {
					super.addToLogs("Wybrano test WEB (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 46);

					//start timer
					long start = System.nanoTime();
					if (isSelectedCurrency == true) {
						super.TestCurrency();

					}
					if (isSelectedGold == true) {
						// The method sets 0 in progress
						disable();
						super.TestGold();
					}
					//stop timer
					long elapsedTime = System.nanoTime() - start;
					super.setTimer(elapsedTime);

				} else {

					super.addToLogs("Wybrano test MOBILE (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 49);
					long start = System.nanoTime();
					if (isSelectedCurrency == true) {
						super.TestCurrency();
					}
					if (isSelectedGold == true) {
						// The method sets 0 in progress
						disable();
						super.TestGold();
					}
					long elapsedTime = System.nanoTime() - start;
					super.setTimer(elapsedTime);
				}
				super.afterTest();
				// Enable all elements on the screen
				enable();
			}

			else {
				JOptionPane.showMessageDialog(null, "Nie zaznaczono ¿adnych elementów do testowania!");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// Disable all elements after click start
	protected void disable() {
		for (Node node : JavaFX.root.getChildren()) {
			node.setDisable(true);
			super.visable();
		}
	}

	// Enable all elements after tests
	protected void enable() {
		for (Node node : JavaFX.root.getChildren()) {
			node.setDisable(false);
		}
		// new list of lists with marked currency
		super.listOfListsCurrency = new ArrayList<ArrayList<ObjectAllAboutCurrencyCSV>>();
		super.continentName= new ArrayList<String>();
		super.invisable();
	}
}
