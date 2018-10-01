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
public class Analysis implements Runnable {
	TestControl tc = TestControl.getInstance();
	Logs logi = Logs.getInstance();
	ProgressBar pbClass = new ProgressBar();
	
	public String web_mobile;
	public String browserName;
	
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
						tc.listOfListsCurrency.add(tc.CurrenciesOfAfrica);
						tc.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "SouthAmerica") {
						tc.listOfListsCurrency.add(tc.CurrenciesOfSouthAmerica);
						tc.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "NorthAmerica") {
						tc.listOfListsCurrency.add(tc.CurrenciesOfNorthAmerica);
						tc.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "Australia") {
						tc.listOfListsCurrency.add(tc.CurrenciesOfAustralia);
						tc.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "Asia") {
						tc.listOfListsCurrency.add(tc.CurrenciesOfAsia);
						tc.continentName.add(item.returnContinentPolishName());
					}
					if (item.returnContinentName() == "Europe") {
						tc.listOfListsCurrency.add(tc.CurrenciesOfEurope);
						tc.continentName.add(item.returnContinentPolishName());
					}
				}
			}
			if (JavaFX.goldTest.isSelected() == true) {
				isSelectedGold = true;
				tc.continentName.add("Z³oto");
			}
			// Select browser
			if (isSelectedCurrency == true || isSelectedGold == true) {
				if (JavaFX.Chrome.isSelected()) {
						tc.browser = REPO.Browsers.Chrome;
						logi.addToLogs("Wybrano Chrome (UserGUI)", getClass().getName().toString(),
								Thread.currentThread().getStackTrace()[1].getMethodName(), 21);
				
				} else if (JavaFX.Opera.isSelected()) {
					tc.browser = REPO.Browsers.Opera;
					logi.addToLogs("Wybrano Opera (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 25);
				} else if (JavaFX.IE.isSelected()) {
					tc.browser = REPO.Browsers.IE;
					logi.addToLogs("Wybrano Internet Explorer (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 29);
				} else if (JavaFX.Edge.isSelected()) {
					tc.browser = REPO.Browsers.Edge;
					logi.addToLogs("Wybrano Edge (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 33);
				} else {
					tc.browser = REPO.Browsers.Firefox;
					logi.addToLogs("Wybrano Mozilla Firefox (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 37);
				}

				// Disable all elements on the screen after click start
				disable();

				tc.beforeTest();

				logi.addToLogs();
				// choice between Web and Mobile test
				if (JavaFX.webTest.isSelected()==true) {
					logi.addToLogs("Wybrano test WEB (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 46);

					//start timer
					long start = System.nanoTime();
					if (isSelectedCurrency == true) {
						tc.TestCurrency();

					}
					if (isSelectedGold == true) {
						// The method sets 0 in progress
						disable();
						tc.TestGold();
					}
					//stop timer
					long elapsedTime = System.nanoTime() - start;
					TestTimer.setTimer(elapsedTime);

				} else {

					logi.addToLogs("Wybrano test MOBILE (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 49);
					long start = System.nanoTime();
					if (isSelectedCurrency == true) {
						tc.TestCurrency();
					}
					if (isSelectedGold == true) {
						// The method sets 0 in progress
						disable();
						tc.TestGold();
					}
					long elapsedTime = System.nanoTime() - start;
					TestTimer.setTimer(elapsedTime);
				}
				tc.afterTest();
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
	public void disable() {
		for (Node node : JavaFX.root.getChildren()) {
			node.setDisable(true);
			pbClass.visable();
		}
	}

	// Enable all elements after tests
	public void enable() {
		for (Node node : JavaFX.root.getChildren()) {
			node.setDisable(false);
		}
		// new list of lists with marked currency
		tc.listOfListsCurrency = new ArrayList<ArrayList<ObjectAllAboutCurrencyCSV>>();
		tc.continentName= new ArrayList<String>();
		pbClass.invisable();
	}
}
