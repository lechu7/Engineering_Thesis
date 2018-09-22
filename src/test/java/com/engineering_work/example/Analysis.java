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
// Analysis elements after click Start, disable elements after click, enable
//elements after testing
public class Analysis implements Runnable {
	TestControl tc = TestControl.getInstance();
	Logs logi = Logs.getInstance();
	ProgressBar pbClass = new ProgressBar();
	boolean isSelectedGold = false;
	boolean isSelectedCurrency = false;

	Timer timer1 = new Timer();

	public void run() {
		try {
			isSelectedGold = false;
			isSelectedCurrency = false;
			if (JavaFX.goldTest.isSelected() == true) {
				isSelectedGold = true;

			}
			// checks if something is selected and select currency to test
			for (ObjectToTableView item : JavaFX.list) {
				if (item.returnBool() == true) {
					isSelectedCurrency = true;
					if (item.returnContinentName() == "Africa") {

						tc.CurrencySelected.addAll(tc.CurrencyOfAfrica);
					}
					if (item.returnContinentName() == "SouthAmerica") {
						tc.CurrencySelected.addAll(tc.CurrencyOfSouthAmerica);
					}
					if (item.returnContinentName() == "NorthAmerica") {

						tc.CurrencySelected.addAll(tc.CurrencyOfNorthAmerica);
					}
					if (item.returnContinentName() == "Australia") {
						tc.CurrencySelected.addAll(tc.CurrencyOfAustralia);
					}
					if (item.returnContinentName() == "Asia") {
						tc.CurrencySelected.addAll(tc.CurrencyOfAsia);
					}
					if (item.returnContinentName() == "Europe") {
						tc.CurrencySelected.addAll(tc.CurrencyOfEurope);
					}
				}
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
				logi.addToLogs();

				// Disable all elements on window after click Start
				disable();

				tc.beforeTest();

				logi.addToLogs();
				// choice between Web and Mobile test
				if (JavaFX.WebTest.isSelected()) {
					logi.addToLogs("Wybrano test WEB (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 46);

					long start = System.nanoTime();

					if (isSelectedCurrency == true) {
						tc.TestWebCurrency();

					}
					if (isSelectedGold == true) {
						// Method to set 0 in progress
						disable();
						tc.TestWebGold();
					}
					long elapsedTime = System.nanoTime() - start;
					JavaFX.setTimer(elapsedTime);

				} else {

					logi.addToLogs("Wybrano test MOBILE (UserGUI)", getClass().getName().toString(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), 49);
					long start = System.nanoTime();
					if (isSelectedCurrency == true) {
						tc.TestMobileCurrency();
					}
					if (isSelectedGold == true) {
						// Method to set 0 in progress
						disable();
						tc.TestMobileGold();
					}
					long elapsedTime = System.nanoTime() - start;
				}
				logi.addToLogs();
				tc.afterTest();
				// Enable all elements on window
				enable();
			}

			else {
				JOptionPane.showMessageDialog(null, "Nie zaznaczono ¿adnych elementów do testowania!");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// Disable all elements after click Start
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
		// new CurrencySelected list
		tc.CurrencySelected = new ArrayList<ObjectAllAboutCurrencyCSV>();
		pbClass.invisable();
		JavaFX.MobileTest.setDisable(true);// ZASLEPKA
	}
}
