package com.engineering_thesis.example;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Label;

public class JavaFX extends PreparationCSV {

	// Thread to analysis action after click "start"
	Thread threadAnalysis;
	protected static StackPane root;

	// RadioButton webTest
	protected static RadioButton webTest;
	// RadioButton mobileTest
	protected static RadioButton mobileTest;

	// Radiobuttons of Browser
	protected static RadioButton Firefox;
	protected static RadioButton Chrome;
	protected static RadioButton Opera;
	protected static RadioButton IE;
	protected static RadioButton Edge;

	// Radiobuttons for all Or selected Currency test
	protected static RadioButton all;
	protected static RadioButton selected;

	protected static ObservableList<ObjectToTableView> list;
	TableView<ObjectToTableView> table;

	// Elements of progressbar
	protected static ProgressBar pb;
	protected static ProgressIndicator pi;
	protected static Label progress;
	protected static Label progressCurrencyInfo;
	// Label with time of test after test
	protected static Label timer;

	// checkbox to test gold exchange rate
	protected static CheckBox goldTest;


	// Button start
	protected static Button start;

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// clear the file logs.txt
		super.clearFileLogs();

		// Preparing lists from CSV
		preparingCSVandLists();

		// Group for radiobuttons that select the test
		final ToggleGroup typeOfTestGroup = new ToggleGroup();
		// RadioButton webTest
		webTest = new RadioButton();
		webTest.setText("Test Webowy");
		webTest.setTranslateX(-100);
		webTest.setTranslateY(-220);
		webTest.setSelected(true);
		webTest.setToggleGroup(typeOfTestGroup);
		webTest.setOnAction(e -> {
			Firefox.setDisable(false);
			Chrome.setDisable(false);
			Opera.setDisable(false);
			IE.setDisable(false);
			Edge.setDisable(false);
		});

		// RadioButton mobileTest
		mobileTest = new RadioButton();
		mobileTest.setText("Test Mobilny");
		mobileTest.setTranslateX(100);
		mobileTest.setTranslateY(-220);
		mobileTest.setToggleGroup(typeOfTestGroup);
		mobileTest.setOnAction(e -> {
			Firefox.setDisable(true);
			Chrome.setDisable(false);
			Opera.setDisable(true);
			IE.setDisable(true);
			Edge.setDisable(true);

			Chrome.setSelected(true);
		});


		final ToggleGroup groupOfBrowsers = new ToggleGroup();
		// RadioButton Firefox
		Firefox = new RadioButton();
		Firefox.setTranslateX(-220);
		Firefox.setTranslateY(-140);
		Firefox.setSelected(true);
		Firefox.setToggleGroup(groupOfBrowsers);
		// Image Firefox
		Image FirefoxImage = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images/firefox.png"));
		ImageView imgViewFirefox = new ImageView(FirefoxImage);
		imgViewFirefox.setTranslateX(-220);
		imgViewFirefox.setTranslateY(-180);

		// RadioButton Chrome
		Chrome = new RadioButton();
		Chrome.setTranslateX(-110);
		Chrome.setTranslateY(-140);
		Chrome.setToggleGroup(groupOfBrowsers);
		// Image Chrome
		Image ChromeImage = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images/chrome.png"));
		ImageView imgViewChrome = new ImageView(ChromeImage);
		imgViewChrome.setTranslateX(-110);
		imgViewChrome.setTranslateY(-180);

		// RadioButton Opera
		Opera = new RadioButton();
		Opera.setTranslateX(0);
		Opera.setTranslateY(-140);
		Opera.setToggleGroup(groupOfBrowsers);
		// Image Opera
		Image OperaImage = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images/opera.png"));
		ImageView imgViewOpera = new ImageView(OperaImage);
		imgViewOpera.setTranslateX(0);
		imgViewOpera.setTranslateY(-180);

		// RadioButton IE
		IE = new RadioButton();
		IE.setTranslateX(110);
		IE.setTranslateY(-140);
		IE.setToggleGroup(groupOfBrowsers);
		// Image IE
		Image IEImage = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images/IE.png"));
		ImageView imgViewIE = new ImageView(IEImage);
		imgViewIE.setTranslateX(110);
		imgViewIE.setTranslateY(-180);

		// RadioButton Edge
		Edge = new RadioButton();
		Edge.setTranslateX(220);
		Edge.setTranslateY(-140);
		Edge.setToggleGroup(groupOfBrowsers);
		// Image IE
		Image EdgeImage = new javafx.scene.image.Image(getClass().getResourceAsStream("/Images/Edge.png"));
		ImageView imgViewEdge = new ImageView(EdgeImage);
		imgViewEdge.setTranslateX(220);
		imgViewEdge.setTranslateY(-180);

		// TableView with selection of Continents that include Currency
		// Add continents to list
		list = FXCollections.observableArrayList();
		list.add(new ObjectToTableView(false, "Afryka", "Africa", super.CurrenciesOfAfrica));
		list.add(new ObjectToTableView(false, "Ameryka Po³udniowa", "SouthAmerica", super.CurrenciesOfSouthAmerica));
		list.add(new ObjectToTableView(false, "Ameryka Pó³nocna", "NorthAmerica", super.CurrenciesOfNorthAmerica));
		list.add(new ObjectToTableView(false, "Australia", "Australia", super.CurrenciesOfAustralia));
		list.add(new ObjectToTableView(false, "Azja", "Asia", super.CurrenciesOfAsia));
		list.add(new ObjectToTableView(false, "Europa", "Europe", super.CurrenciesOfEurope));
		// new table
		table = new TableView<ObjectToTableView>();
		table.setEditable(true);
		table.setItems(list);
		// Create and add column1 with CheckBoxes
		TableColumn<ObjectToTableView, Boolean> Column1 = new TableColumn<ObjectToTableView, Boolean>("Testowanie");
		Column1.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, Boolean>("run"));
		Column1.setCellFactory(column -> new CheckBoxTableCell<ObjectToTableView, Boolean>());
		table.getColumns().add(Column1);
		// Create and add column2 with name of continents
		TableColumn<ObjectToTableView, String> Column2 = new TableColumn<ObjectToTableView, String>("Nazwa kontynentu");
		Column2.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, String>("ContinentName"));
		table.getColumns().add(Column2);
		// Create and add column3 with number of currencies
		TableColumn<ObjectToTableView, Integer> Column3 = new TableColumn<ObjectToTableView, Integer>("Liczba walut");
		Column3.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, Integer>("CurrencyCountContinent"));
		table.getColumns().add(Column3);
		// Set size of tableView
		double Xsize = 296;
		double Ysize = 180;
		table.setMinWidth(Xsize);
		table.setMaxWidth(Xsize);
		table.setMinHeight(Ysize);
		table.setMaxHeight(Ysize);
		// Set position of tableView
		table.setTranslateY(-20);
		table.setTranslateX(-130);

		final ToggleGroup allOrChosenCurrencyGroup = new ToggleGroup();
		all = new RadioButton();
		all.setText("Wszystkie waluty (Iloœæ: " + super.CurrenciesAll.size() + ")");
		all.setTranslateX(125);
		all.setTranslateY(-65);
		all.setToggleGroup(allOrChosenCurrencyGroup);
		// When you click "all Currency", all CheckBoxes of tableView have been checked
		all.setOnAction(e -> {
			try {
				super.addToLogs();
				super.addToLogs("Kliknieto Wszystkie Waluty (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 224);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			table.setEditable(false);
			for (ObjectToTableView item : list) {
				item.SetRunProperty(true);
			}
			table.refresh();
			try {
				super.addToLogs();
				super.addToLogs("Odznaczone checkboxy w tableView, tableView nieaktywne (UserGUI)",
						getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						224);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		selected = new RadioButton();
		selected.setText("Wybrane waluty z tabeli kontynentów");
		selected.setTranslateX(150);
		selected.setTranslateY(-25);
		selected.setToggleGroup(allOrChosenCurrencyGroup);
		selected.setSelected(true);
		selected.setOnAction(e -> {
			table.setEditable(true);
			try {
				super.addToLogs();
				super.addToLogs("Klikniêto wybrane waluty, tableView aktywne (UserGUI)",
						getClass().getName().toString(), Thread.currentThread().getStackTrace()[1].getMethodName(),
						249);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for (ObjectToTableView item : list) {
				item.SetRunProperty(false);
			}
			table.refresh();
		});

		// CheckBox- testing of gold exchange rate.
		goldTest = new CheckBox();
		goldTest.setText("Testowanie kursu z³ota");
		goldTest.setTranslateX(112);
		goldTest.setTranslateY(25);

		// PROGRESS BAR
		progress = new Label();
		progress.setTranslateX(0);
		progress.setTranslateY(100);
		progress.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		pb = new ProgressBar(0);
		pi = new ProgressIndicator(0);

		pi.setMinWidth(50);
		pi.setMinHeight(30);

		pb.setMinWidth(500);
		pb.setMinHeight(30);

		pb.setTranslateX(-30);
		pb.setTranslateY(140);

		pi.setTranslateX(250);
		pi.setTranslateY(145);

		progressCurrencyInfo = new Label();
		progressCurrencyInfo.setText("Uruchamianie testu...");
		progressCurrencyInfo.setTranslateX(0);
		progressCurrencyInfo.setTranslateY(175);
		progressCurrencyInfo.setFont(Font.font("Arial", 17));

		pb.setVisible(false);
		pi.setVisible(false);
		progress.setVisible(false);
		progressCurrencyInfo.setVisible(false);

		// TestTimer
		timer = new Label();
		timer.setText("czas");
		timer.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		timer.setTranslateX(0);
		timer.setTranslateY(135);
		timer.setVisible(false);

		// Button start
		start = new Button();
		start.setTranslateX(0);
		start.setTranslateY(220);
		start.setText("Start");
		// Change font of button start
		start.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		start.setOnAction(e -> {
			try {
				// Preparing lists from CSV
				preparingCSVandLists();

				progressCurrencyInfo.setText("Uruchamianie testu...");
				progress.setText("");
				super.addToLogs();
				super.addToLogs("Kliknieto start (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 144);
				super.addToLogs();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			threadAnalysis = new Thread(new Analysis());
			threadAnalysis.start();
		});
		// Add elements to root (layout)
		root = new StackPane();
		root.getChildren().add(start);
		root.getChildren().add(webTest);
		root.getChildren().add(mobileTest);
		root.getChildren().add(Firefox);
		root.getChildren().add(Chrome);
		root.getChildren().add(Opera);
		root.getChildren().add(IE);
		root.getChildren().add(Edge);
		root.getChildren().add(imgViewFirefox);
		root.getChildren().add(imgViewChrome);
		root.getChildren().add(imgViewOpera);
		root.getChildren().add(imgViewIE);
		root.getChildren().add(imgViewEdge);
		root.getChildren().add(table);
		root.getChildren().add(all);
		root.getChildren().add(selected);
		root.getChildren().add(progress);
		root.getChildren().add(pb);
		root.getChildren().add(pi);
		root.getChildren().add(progressCurrencyInfo);
		root.getChildren().add(goldTest);
		root.getChildren().add(timer);
		// screen
		Scene scene = new Scene(root, 600, 500);
		// when you click close (X)
		primaryStage.setOnCloseRequest(event -> {
			try {
				super.addToLogs();
				super.addToLogs("Zamkniêto program (UserGUI- klikniecie X)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 144);
				super.driver.quit();
			} catch (Exception e) {
			}
			System.exit(0);
		});

		// Icon set
		javafx.scene.image.Image icon = new javafx.scene.image.Image(
				getClass().getResourceAsStream("/Images/icon.png"));
		primaryStage.getIcons().add(icon);

		primaryStage.setTitle("NBP API Tests");
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.show();

	}

	public static void main(String[] args) {

		launch(args);
	}

	@SuppressWarnings("static-access")
	protected void preparingCSVandLists() throws IOException {
		if (super.CSVCurrenciesOfEurope == null || super.CSVCurrenciesOfAsia == null
				|| super.CSVCurrenciesOfAustralia == null || super.CSVCurrenciesOfNorthAmerica == null
				|| super.CSVCurrenciesOfSouthAmerica == null || super.CSVCurrenciesOfAfrica == null) {
			super.readCSVDate();
		}

		if (super.CurrenciesOfEurope == null || super.CurrenciesOfAsia == null || super.CurrenciesOfAustralia == null
				|| super.CurrenciesOfNorthAmerica == null || super.CurrenciesOfSouthAmerica == null
				|| super.CurrenciesOfAfrica == null || super.CurrenciesAll == null) {
			super.CurrenciesOfEurope = new ArrayList<ObjectAllAboutCurrencyCSV>();
			super.CurrenciesOfAsia = new ArrayList<ObjectAllAboutCurrencyCSV>();
			super.CurrenciesOfAustralia = new ArrayList<ObjectAllAboutCurrencyCSV>();
			super.CurrenciesOfNorthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			super.CurrenciesOfSouthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			super.CurrenciesOfAfrica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			super.CurrenciesAll = new ArrayList<ObjectAllAboutCurrencyCSV>();

			super.CurrenciesOfEurope = super.CSVCurrenciesOfEurope;
			super.CurrenciesOfAsia = super.CSVCurrenciesOfAsia;
			super.CurrenciesOfAustralia = super.CSVCurrenciesOfAustralia;
			super.CurrenciesOfNorthAmerica = super.CSVCurrenciesOfNorthAmerica;
			super.CurrenciesOfSouthAmerica = super.CSVCurrenciesOfSouthAmerica;
			super.CurrenciesOfAfrica = super.CSVCurrenciesOfAfrica;
		}
		if (continentName == null || listOfListsCurrency == null || CurrenciesAll == null) {
			super.continentName = new ArrayList<String>();
			super.listOfListsCurrency = new ArrayList<ArrayList<ObjectAllAboutCurrencyCSV>>();

			// add all lists to "CountriesAll" list
			super.CurrenciesAll.addAll(super.CurrenciesOfEurope);
			super.CurrenciesAll.addAll(super.CurrenciesOfAsia);
			super.CurrenciesAll.addAll(super.CurrenciesOfAustralia);
			super.CurrenciesAll.addAll(super.CurrenciesOfNorthAmerica);
			super.CurrenciesAll.addAll(super.CurrenciesOfSouthAmerica);
			super.CurrenciesAll.addAll(super.CurrenciesOfAfrica);
		}
	}
}