package com.engineering_work.example;

<<<<<<< HEAD
=======
import java.io.File;
import java.io.FileNotFoundException;
>>>>>>> origin/master
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javafx.application.Platform;

public class TestTimer {
	static DecimalFormat df = new DecimalFormat("0.000");
	TestControl tc = TestControl.getInstance();
	static Logs logi = Logs.getInstance();
	
<<<<<<< HEAD
	//The method that shows on the label, which part of tests is being done 
=======
	//Method to set on the label parts of tests
>>>>>>> origin/master
	public static void setProgressLabel(int progress, int maxProgress) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				JavaFX.progress.setText("Progres: ("+progress+"/"+maxProgress+")");
			}
		});
	}
<<<<<<< HEAD
	//The method that shows time of all tests on the label 
=======
	//Method to set on the label time of all tests
>>>>>>> origin/master
	public static void setTimer(long time) {
		final double returnTime=time/1000000000F;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				JavaFX.timer.setText("Czas wykonywania ca³ego testu = "+df.format(returnTime)+" s.");
			}
		});
	}
<<<<<<< HEAD
	//Method that saves value of test time to file CSV
=======
	//Method to save to file CSV value of test time
>>>>>>> origin/master
	public static void saveTimeToCSV(String Web_Mobile, String continent,long elapsedTime) throws IOException
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
	        logi.addToLogs();
	    	logi.addToLogs("Zapisano czas dla "+continent,"TestTimer","saveTimeToCSV", 51);
	    	logi.addToLogs();
	}
}
