package com.engineering_work.example;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public abstract class Logs extends Application {
	 //Add empty line to file logs
	protected void addToLogs()throws IOException
	{
		try		
		{
			FileOutputStream fileStream = new FileOutputStream(new File("logs.txt"),true);
			//You use iso-8859-2 encoding to have polish chars
			OutputStreamWriter writer = new OutputStreamWriter(fileStream, "ISO-8859-2");
			writer.write("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	 //Adding log and automatically saving to logs.txt in format ISO-8859-2
	protected void addToLogs(String log, String className, String methodName, int line)throws IOException
	{
		try		
		{
			FileOutputStream fileStream = new FileOutputStream(new File("logs.txt"),true);
			//You use iso-8859-2 encoding to have polish chars
			OutputStreamWriter writer = new OutputStreamWriter(fileStream, "ISO-8859-2");
			writer.write("\n"+getCurrentData()+" "+log+" [*C*:"+className+";/*M*:"+methodName+";/*L*:"+line+"]");
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	//Clear file logs.txt
	protected void clearFileLogs() throws IOException
	{
		try		
		{
			FileOutputStream fileStream = new FileOutputStream(new File("logs.txt"),false);
			//You use iso-8859-2 encoding to have polish chars
			OutputStreamWriter writer = new OutputStreamWriter(fileStream,"ISO-8859-2");
			writer.write("");
			writer.close();
		System.out.println("Clear file logs.txt");
		}
		catch(Exception e)
		{		
			System.out.println("***Warning*** It doesn't clear file logs.txt");
		}
		
	}
	//Method that you give current data to logs
	String getCurrentData()
	{
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now).toString();
	}



}
