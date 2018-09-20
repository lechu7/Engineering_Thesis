package com.engineering_work.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;

//New thread
// Analysis elements after click Start, disable elements after click, enable
//elements after testing
public class Analysis  implements Runnable {
	TestControl tc =TestControl.getInstance();
	Logs logi=Logs.getInstance();

	public void run(){
		try {
			
			if	(JavaFX.Chrome.isSelected()) {
				tc.browser=REPO.Browsers.Chrome;
				logi.addToLogs("Wybrano Chrome (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),21);
			}
			else if	(JavaFX.Opera.isSelected()) {
				tc.browser=REPO.Browsers.Opera;
				logi.addToLogs("Wybrano Opera (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),25);
			}
			else if	(JavaFX.IE.isSelected()) {
				tc.browser=REPO.Browsers.IE;
				logi.addToLogs("Wybrano Internet Explorer (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),29);
			}
			else if	(JavaFX.Edge.isSelected()) {
				tc.browser=REPO.Browsers.Edge;
				logi.addToLogs("Wybrano Edge (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),33);
			}
			else{
				tc.browser=REPO.Browsers.Firefox;
				logi.addToLogs("Wybrano Mozilla Firefox (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),37);
			}
			logi.addToLogs();
			// Disable all elements on window after click Start
			disable();
			tc.beforeTest();
			logi.addToLogs();
			// choice between Web and Mobile test
			if (JavaFX.WebTest.isSelected()) {
				logi.addToLogs("Wybrano test WEB (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),46);
				tc.TestWeb();
			} else {
				
				logi.addToLogs("Wybrano test MOBILE (UserGUI)",getClass().getName().toString(),Thread.currentThread().getStackTrace()[1].getMethodName(),49);
				tc.TestMobile();
			}
			logi.addToLogs();
			tc.afterTest();
			// Enable all elements on window
			enable();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	// Disable all elements after click Start
		public void disable() {
			for (Node node : JavaFX.root.getChildren()) {
				node.setDisable(true);
			}
		}

		// Enable all elements after tests
		public void enable() {
			for (Node node : JavaFX.root.getChildren()) {
				node.setDisable(false);
			}
			JavaFX.MobileTest.setDisable(true);//ZASLEPKA
		}
}

