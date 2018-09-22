package com.engineering_work.example;
import javafx.scene.control.Label;
import javafx.application.Platform;

public class ProgressBar {
	
	
	
	 public void changedProgress(double new_val,double all_val,String currencyName) {
         JavaFX.pb.setProgress(new_val/all_val);
         JavaFX.pi.setProgress(new_val/all_val);
         //JavaFX.progressCurrencyInfo.setText("Waluta "+currencyName+". ("+new_val+"/"+all_val+")");
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
	 }
	 //Visable progress bar and other elements 
	 public void invisable()
	 {
		 JavaFX.pb.setVisible(false);
		 JavaFX.pi.setVisible(false);
		 JavaFX.progress.setVisible(false);
	 }
	 

}
