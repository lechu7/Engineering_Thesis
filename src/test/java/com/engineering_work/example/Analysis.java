package com.engineering_work.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;

//New thread
// Analysis elements after click Start, disable elements after click, enable
//elements after testing
public class Analysis  implements Runnable {
	TestControl tc = new TestControl();
	
	public void run(){
		try {

			// Disable all elements on window after click Start
			disable();
			tc.beforeTest();

			// choice between Web and Mobile test
			if (JavaFX.WebTest.isSelected()) {
				tc.TestWeb();
			} else {
				tc.TestMobile();
			}
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
		}
}

