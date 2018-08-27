package com.engineering_work.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class PreparationCSV {
	Logs logi = Logs.getInstance();

	public ArrayList<ObjectAllAboutCurrencyCSV> CSVCountriesOfEurope = new ArrayList<ObjectAllAboutCurrencyCSV>();
	public ArrayList<ObjectAllAboutCurrencyCSV> CSVCountriesOfAsia = new ArrayList<ObjectAllAboutCurrencyCSV>();
	public ArrayList<ObjectAllAboutCurrencyCSV> CSVCountriesOfAustralia = new ArrayList<ObjectAllAboutCurrencyCSV>();
	public ArrayList<ObjectAllAboutCurrencyCSV> CSVCountriesOfNorthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
	public ArrayList<ObjectAllAboutCurrencyCSV> CSVCountriesOfSouthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
	public ArrayList<ObjectAllAboutCurrencyCSV> CSVCountriesOfAfrica = new ArrayList<ObjectAllAboutCurrencyCSV>();

	public ArrayList<ObjectAllAboutCurrencyCSV> readCSVDate() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("data.csv"));
		ArrayList<ObjectAllAboutCurrencyCSV> listCSV = new ArrayList<ObjectAllAboutCurrencyCSV>();
		String[] csvCell = null;

		// Read the columnsName
		reader.readNext();
		while ((csvCell = reader.readNext()) != null) {
			listCSV.add(readCellCSV(reader, csvCell));
		}
		logi.addToLogs("Sukces. Pobrano dane z pliku CSV wszystkie kursy", getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 29);
		return listCSV;
	}

	ObjectAllAboutCurrencyCSV readCellCSV(CSVReader reader, String[] csvCell) throws IOException {

		ObjectAllAboutCurrencyCSV tmpCell = null;

		String[] TMPArray = csvCell[0].split(";");
		tmpCell = new ObjectAllAboutCurrencyCSV(TMPArray[0].toString(), TMPArray[1].toString(), TMPArray[2].toString(),
				TMPArray[3].toString(), TMPArray[4].toString(), TMPArray[5].toString(), TMPArray[6].toString(),
				TMPArray[7].toString(), TMPArray[8].toString(), TMPArray[9].toString());
		logi.addToLogs("Pobrano dane z pliku CSV dla " + TMPArray[0].toString(), getClass().getName().toString(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), 42);

		// Preparation list countries in terms of continents
		int continentIndex = -1;
		for (int i = 0; i < TMPArray.length; i++) {
			int tmp = TMPArray[i].compareTo("X");
			if (tmp == 0) {
				continentIndex = i;

				switch (continentIndex) {
				case -1:
					logi.addToLogs("***ERROR***Nie znaleziono indeksu kontynentu dla waluty " + TMPArray[1].toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							54);
					break;
				case 3:
					CSVCountriesOfEurope.add(tmpCell);
					logi.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Europa".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							60);
					break;

				case 4:
					CSVCountriesOfAsia.add(tmpCell);
					logi.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Azja".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							66);
					break;
				case 5:
					CSVCountriesOfAustralia.add(tmpCell);
					logi.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Australia".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							73);
					break;
				case 6:
					CSVCountriesOfNorthAmerica.add(tmpCell);
					logi.addToLogs(
							"Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Płn. Ameryka".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							79);
					break;
				case 7:
					CSVCountriesOfSouthAmerica.add(tmpCell);
					logi.addToLogs(
							"Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Płd. Ameryka".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							86);
					break;
				case 8:
					CSVCountriesOfAfrica.add(tmpCell);
					logi.addToLogs("Znaleziono indeks kontynentu dla waluty " + TMPArray[1] + "-Afryka".toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							79);
					break;
				default:
					logi.addToLogs("***ERROR***Nie znaleziono indeksu kontynentu dla waluty " + TMPArray[1].toString(),
							getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
							83);
					break;
				}
			}
		}
		return tmpCell;
	}
}
