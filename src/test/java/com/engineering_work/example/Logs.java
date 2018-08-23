package com.engineering_work.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Logs {
	//Singleton for class Logs 
	private static Logs instance = null;
	 public static Logs getInstance() {
	      if(instance == null) {
	         instance = new Logs();
	      }
	      return instance;
	   }
	 //Add logs and automatically save to logs.txt
	public void addToLogs(String log, String className, String methodName, int line)throws IOException
	{
		try		
		{
			FileWriter file = new FileWriter("logs.txt", true);
			BufferedWriter out = new BufferedWriter(file);
			out.write("\n"+getCurrentData()+" "+log+" [*C*:"+className+";/*M*:"+methodName+";/*L*:"+line+"]");
			out.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	//Clear file logs.txt after 
	public void clearFileLogs() throws IOException
	{
		try		
		{
			FileWriter file = new FileWriter("logs.txt", false);
			BufferedWriter out = new BufferedWriter(file);
			out.write("");
			out.close();
		System.out.println("Wyczyszczono plik logs.txt [C:"+this.getClass().getName().toString()+";M:"+this.getClass().getEnclosingMethod().getName().toString()+";L:30");
		}
		catch(Exception e)
		{		
			System.out.println("***Warning*** Nie wyczyszczono pliku logs.txt [C:"+this.getClass().getName().toString()+";M:"+this.getClass().getEnclosingMethod().getName().toString()+";L:46");
		}
		
	}
	//Method to get current data to logs
	String getCurrentData()
	{
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now).toString();
	}


}
