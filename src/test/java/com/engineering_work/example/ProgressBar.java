package com.engineering_work.example;
import javafx.scene.control.Label;

import java.awt.EventQueue;

import javax.swing.JLabel;

import javafx.application.Platform;

public class ProgressBar {
	
	//Method to change progress in progressBar
	 public void changedProgress(double new_val,double all_val,final String messageText) {
         JavaFX.pb.setProgress(new_val/all_val);
         JavaFX.pi.setProgress(new_val/all_val);
         //Change the text under progress bar
         Platform.runLater(new Runnable() {
             @Override public void run() {
            	  JavaFX.progressCurrencyInfo.setText(messageText);
            	  }
       });
       
	 }
	 //Visable progress bar and other elements 
	 public void visable()
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
		 JavaFX.progressCurrencyInfo.setDisable(false);;
	 }
	 //Visable progress bar and other elements 
	 public void invisable()
	 {
		 JavaFX.pb.setVisible(false);
		 JavaFX.pi.setVisible(false);
		 JavaFX.progress.setVisible(false);
		 JavaFX.progressCurrencyInfo.setVisible(false);
	 }
	

}
