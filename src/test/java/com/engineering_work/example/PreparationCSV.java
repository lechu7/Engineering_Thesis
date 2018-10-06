package com.engineering_work.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public abstract class PreparationCSV  extends TestControl{


	protected static ArrayList<ObjectAllAboutCurrencyCSV> CSVCurrenciesOfEurope;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CSVCurrenciesOfAsia;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CSVCurrenciesOfAustralia;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CSVCurrenciesOfNorthAmerica;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CSVCurrenciesOfSouthAmerica;
	protected static ArrayList<ObjectAllAboutCurrencyCSV> CSVCurrenciesOfAfrica;

	protected ArrayList<ObjectAllAboutCurrencyCSV> readCSVDate() throws IOException {
		CSVCurrenciesOfEurope = new ArrayList<ObjectAllAboutCurrencyCSV>();
		CSVCurrenciesOfAsia = new ArrayList<ObjectAllAboutCurrencyCSV>();
		CSVCurrenciesOfAustralia = new ArrayList<ObjectAllAboutCurrencyCSV>();
		CSVCurrenciesOfNorthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
		CSVCurrenciesOfSouthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
		CSVCurrenciesOfAfrica = new ArrayList<ObjectAllAboutCurrencyCSV>();
		
		CSVReader reader = new CSVReader(new FileReader("data.csv"));
		ArrayList<ObjectAllAboutCurrencyCSV> listCSV = new ArrayList<ObjectAllAboutCurrencyCSV>();
		String[] csvCell = null;

		// Read the name of columns 
		reader.readNext();
		
		while ((csvCell = reader.readNext()) != null) {
			listCSV.add(readCellCSV(reader, csvCell));
		}
		super.addToLogs("Sukces. Pobrano dane z pliku CSV wszystkie kursy", getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 29);
		return listCSV;
	}

	ObjectAllAboutCurrencyCSV readCellCSV(CSVReader reader, String[] csvCell) throws IOException {

		ObjectAllAboutCurrencyCSV tmpCell = null;

		String[] TMPArray = csvCell[0].split(";");
		tmpCell = new ObjectAllAboutCurrencyCSV(TMPArray[0].toString(), TMPArray[1].toString(), TMPArray[2].toString(),
				TMPArray[3].toString(), TMPArray[4].toString(), TMPArray[5].toString(), TMPArray[6].toString(),
				TMPArray[7].toString(), TMPArray[8].toString(), TMPArray[9].toString());
		super.addToLogs("Pobrano dane z pliku CSV dla " + TMPArray[0].toString(), getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 42);

		// Preparation lists of currencies on the basis of continents
		int continentIndex = -1;
		for (int i = 0; i < TMPArray.length; i++) {
			int tmp = TMPArray[i].compareTo("X");
			if (tmp == 0) {
				continentIndex = i;

				switch (continentIndex) {
				case -1:
					super.addToLogs("***ERROR***Nie znaleziono indeksu kontynentu dla waluty " + TMPArray[1].toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							54);
					break;
				case 3:
					CSVCurrenciesOfEurope.add(tmpCell);
					super.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Europa".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							60);
					break;

				case 4:
					CSVCurrenciesOfAsia.add(tmpCell);
					super.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Azja".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							66);
					break;
				case 5:
					CSVCurrenciesOfAustralia.add(tmpCell);
					super.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Australia".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							73);
					break;
				case 6:
					CSVCurrenciesOfNorthAmerica.add(tmpCell);
					super.addToLogs(
							"Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Płn. Ameryka".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							79);
					break;
				case 7:
					CSVCurrenciesOfSouthAmerica.add(tmpCell);
					super.addToLogs(
							"Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Płd. Ameryka".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							86);
					break;
				case 8:
					CSVCurrenciesOfAfrica.add(tmpCell);
					super.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Afryka".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							79);
					break;
				default:
					super.addToLogs("***ERROR***Nie znaleziono indeksu kontynentu dla waluty " + TMPArray[1].toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							83);
					break;
				}
			}
		}
		return tmpCell;
	}
}
