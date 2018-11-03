package com.engineering_work.example;
import javafx.scene.control.Label;

import java.awt.EventQueue;

import javax.swing.JLabel;

import javafx.application.Platform;

public abstract class ProgressBar extends Logs  {
	
	
	//Method that you change progress for progressBar
	 protected void changedProgress(double new_val,double all_val,final String messageText) {
		 JavaFX.pb.setProgress(new_val/all_val);
		 JavaFX.pi.setProgress(new_val/all_val);
         //Changing the text under progress bar
         Platform.runLater(new Runnable() {
             @Override public void run() {
            	 JavaFX.progressCurrencyInfo.setText(messageText+" ("+(int)new_val+"/"+(int)all_val+")");
            	  }
       });
       
	 }
	 //Visible progress bar and other elements 
	 protected void visable()
	 {
		 CheckExchange.iterator=0;
		 JavaFX.pb.setProgress(0);
		 JavaFX.pi.setProgress(0);
		 JavaFX.pb.setVisible(true);
		 JavaFX.pi.setVisible(true);
		 JavaFX.pb.setDisable(false);;
		 JavaFX.pi.setDisable(false);;
		 JavaFX.progress.setVisible(true);
		 JavaFX.progress.setDisable(false);;
		 
		 JavaFX.progressCurrencyInfo.setVisible(true);
		 JavaFX.progressCurrencyInfo.setDisable(false);
		 
		 //Setting invisible for label timer
		 JavaFX.timer.setVisible(false);

	 }
	 //Invisible progress bar and other elements 
	 protected void invisable()
	 {
		 JavaFX.pb.setVisible(false);
		 JavaFX.pi.setVisible(false);
		 JavaFX.progress.setVisible(false);
		 JavaFX.progressCurrencyInfo.setVisible(false);
		 
		//Setting visible for label timer
		 JavaFX.timer.setVisible(true);
		 
		//Deselect mobile test
		 JavaFX.webTest.setSelected(true);
	 }
	

}
