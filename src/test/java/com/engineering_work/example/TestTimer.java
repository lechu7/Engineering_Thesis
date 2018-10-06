package com.engineering_work.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javafx.application.Platform;

public abstract class TestTimer extends ProgressBar{
	static DecimalFormat df = new DecimalFormat("0.000");
	
	//The method that shows on the label, which part of tests is being done 
	public  void setProgressLabel(int progress, int maxProgress) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				JavaFX.progress.setText("Progres: ("+progress+"/"+maxProgress+")");
			}
		});
	}
	//The method that shows time of all tests on the label 
	public  void setTimer(long time) {
		final double returnTime=time/1000000000F;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				JavaFX.timer.setText("Czas wykonywania ca³ego testu = "+df.format(returnTime)+" s.");
			}
		});
	}
	//Method that saves value of test time to file CSV
	public void saveTimeToCSV(String Web_Mobile, String continent,long elapsedTime) throws IOException
	{
		final double returnTime=elapsedTime/1000000000F;
		
		 PrintWriter pw = new PrintWriter(new FileWriter("time.csv",true));
	        StringBuilder sb = new StringBuilder();
	        sb.append(Web_Mobile);
	        sb.append(';');
	        sb.append(TestControl.returnBrowserName().toString());
	        sb.append(';');
	        sb.append(continent);
	        sb.append(';');
	        sb.append(df.format(returnTime));
	        sb.append('\n');
	        
	        pw.write(sb.toString());
	        pw.close();
	        super.addToLogs();
	    	super.addToLogs("Zapisano czas dla "+continent,"TestTimer","saveTimeToCSV", 51);
	    	super.addToLogs();
	}
}
