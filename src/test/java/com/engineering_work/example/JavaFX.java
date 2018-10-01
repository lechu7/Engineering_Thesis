package com.engineering_work.example;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.text.Element;

//import org.apache.commons.collections.functors.PrototypeFactory;

import com.google.common.collect.Table;

import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class JavaFX extends Application {
	TestControl tc = TestControl.getInstance();
	Logs logi = Logs.getInstance();
	PreparationCSV pCSV = new PreparationCSV();
	ProgressBar pbClass = new ProgressBar();

	// Thread to analysis action after click "start" 
	Thread threadAnalysis;
	public static StackPane root;

	// RadioButton webTest
	public static RadioButton webTest;
	// RadioButton mobileTest
	public static RadioButton mobileTest;

	// Radiobuttons of Browser
	public static RadioButton Firefox;
	public static RadioButton Chrome;
	public static RadioButton Opera;
	public static RadioButton IE;
	public static RadioButton Edge;

	// Radiobuttons for all Or selected Currency test
	public static RadioButton all;
	public static RadioButton selected;

	public static ObservableList<ObjectToTableView> list;
	TableView<ObjectToTableView> table;

	// Elements of progressbar
	public static ProgressBar pb;
	public static ProgressIndicator pi;
	public static Label progress;
	public static Label progressCurrencyInfo;
	// Label with time of test after test
	public static Label timer;

	// checkbox to test gold exchange rate
	public static CheckBox goldTest;
	
	// checkbox to test gold exchange rate
	public static CheckBox phisicDevice;

	// Button start
	public static Button start;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// clear the file logs.txt
		logi.clearFileLogs();
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
			phisicDevice.setVisible(false);
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
			phisicDevice.setVisible(true);
		});
		
		// CheckBox- testing on phisic device.
		phisicDevice= new CheckBox();
		phisicDevice.setText("Urz¹dzenie fizyczne");
		phisicDevice.setTranslateX(220);
		phisicDevice.setTranslateY(-220);
		phisicDevice.setVisible(false);

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
		list.add(new ObjectToTableView(false, "Afryka", "Africa", tc.CurrenciesOfAfrica));
		list.add(new ObjectToTableView(false, "Ameryka Po³udniowa", "SouthAmerica", tc.CurrenciesOfSouthAmerica));
		list.add(new ObjectToTableView(false, "Ameryka Pó³nocna", "NorthAmerica", tc.CurrenciesOfNorthAmerica));
		list.add(new ObjectToTableView(false, "Australia", "Australia", tc.CurrenciesOfAustralia));
		list.add(new ObjectToTableView(false, "Azja", "Asia", tc.CurrenciesOfAsia));
		list.add(new ObjectToTableView(false, "Europa", "Europe", tc.CurrenciesOfEurope));
		// new table
		table = new TableView<ObjectToTableView>();
		table.setEditable(true);
		table.setItems(list);
		// Create and add column1 with CheckBoxes
		TableColumn<ObjectToTableView, Boolean> Column1 = new TableColumn<ObjectToTableView, Boolean>("Testowanie");
		Column1.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, Boolean>("run"));
		Column1.setCellFactory(column -> new CheckBoxTableCell());
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
		all.setText("Wszystkie waluty (Iloœæ: " + tc.CurrenciesAll.size() + ")");
		all.setTranslateX(125);
		all.setTranslateY(-65);
		all.setToggleGroup(allOrChosenCurrencyGroup);
		// When you click "all Currency", all CheckBoxes of tableView have been checked
		all.setOnAction(e -> {
			try {
				logi.addToLogs();
				logi.addToLogs("Kliknieto Wszystkie Waluty (UserGUI)", getClass().getName().toString(),
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
				logi.addToLogs();
				logi.addToLogs("Odznaczone checkboxy w tableView, tableView nieaktywne (UserGUI)",
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
				logi.addToLogs();
				logi.addToLogs("Klikniêto wybrane waluty, tableView aktywne (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 249);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for (ObjectToTableView item : list) {
				item.SetRunProperty(false);
			}
			table.refresh();
		});

		// CheckBox- testing of gold exchange rate.
		goldTest= new CheckBox();
		goldTest.setText("Testowanie kursu z³ota");
		goldTest.setTranslateX(112);
		goldTest.setTranslateY(25);

		// PROGRESS BAR
		progress=new Label();
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
				progressCurrencyInfo.setText("Uruchamianie testu...");
				progress.setText("");
				logi.addToLogs();
				logi.addToLogs("Kliknieto start (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 144);
				logi.addToLogs();
				// Preparing lists from CSV
				preparingCSVandLists();
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
		root.getChildren().add(phisicDevice);
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
				logi.addToLogs();
				logi.addToLogs("Zamkniêto program (UserGUI- klikniecie X)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 144);
				tc.driver.quit();
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

	public void preparingCSVandLists() throws IOException {

		if (tc.CurrenciesOfEurope == null || tc.CurrenciesOfAsia == null || tc.CurrenciesOfAustralia == null
				|| tc.CurrenciesOfNorthAmerica == null || tc.CurrenciesOfSouthAmerica == null
				|| tc.CurrenciesOfAfrica == null) {
			tc.CurrenciesOfEurope = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrenciesOfAsia = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrenciesOfAustralia = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrenciesOfNorthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrenciesOfSouthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrenciesOfAfrica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrenciesAll = new ArrayList<ObjectAllAboutCurrencyCSV>();

			tc.continentName = new ArrayList<String>();
			tc.listOfListsCurrency = new ArrayList<ArrayList<ObjectAllAboutCurrencyCSV>>();

			pCSV.readCSVDate();
			tc.CurrenciesOfEurope = pCSV.CSVCurrenciesOfEurope;
			tc.CurrenciesOfAsia = pCSV.CSVCurrenciesOfAsia;
			tc.CurrenciesOfAustralia = pCSV.CSVCurrenciesOfAustralia;
			tc.CurrenciesOfNorthAmerica = pCSV.CSVCurrenciesOfNorthAmerica;
			tc.CurrenciesOfSouthAmerica = pCSV.CSVCurrenciesOfSouthAmerica;
			tc.CurrenciesOfAfrica = pCSV.CSVCurrenciesOfAfrica;

			// add all lists to "CountriesAll" list
			tc.CurrenciesAll.addAll(tc.CurrenciesOfEurope);
			tc.CurrenciesAll.addAll(tc.CurrenciesOfAsia);
			tc.CurrenciesAll.addAll(tc.CurrenciesOfAustralia);
			tc.CurrenciesAll.addAll(tc.CurrenciesOfNorthAmerica);
			tc.CurrenciesAll.addAll(tc.CurrenciesOfSouthAmerica);
			tc.CurrenciesAll.addAll(tc.CurrenciesOfAfrica);
		}
	}

}