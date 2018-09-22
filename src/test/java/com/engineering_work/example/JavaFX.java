package com.engineering_work.example;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.text.Element;

import org.apache.commons.collections.functors.PrototypeFactory;

import com.google.common.collect.Table;

import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
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
	ProgressBar pbClass= new ProgressBar();

	//Thread to analysis action after Start click
	Thread threadAnalysis;
	public static StackPane root;

	// RadioButton WebTest
	public static RadioButton WebTest;
	// RadioButton MobileTest
	public static RadioButton MobileTest;

	// Radiobuttons Browsers
	public static RadioButton Firefox;
	public static RadioButton Chrome;
	public static RadioButton Opera;
	public static RadioButton IE;
	public static RadioButton Edge;

	// Radiobuttons All Or Chosen Currency test
	public static RadioButton All;
	public static RadioButton Chosen;

	public static ObservableList<ObjectToTableView> list;
	TableView<ObjectToTableView> table;

	//Elements to progressbar
	public static ProgressBar pb;
	public static ProgressIndicator pi;
	public static Label progress = new Label();
	public static final Label progressCurrencyInfo = new Label();
	
	public static CheckBox goldTest= new CheckBox();

	// Button Start
	public static Button Start;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// clear the file logs.txt
		logi.clearFileLogs();
		// Preparing lists from CSV
		preparingCSVandLists();

		// Group for radiobutton that select the test
		final ToggleGroup typeOfTestGroup = new ToggleGroup();
		// RadioButton WebTest
		WebTest = new RadioButton();
		WebTest.setText("Test Webowy");
		WebTest.setTranslateX(-100);
		WebTest.setTranslateY(-220);
		WebTest.setSelected(true);
		WebTest.setToggleGroup(typeOfTestGroup);

		// RadioButton MobileTest
		MobileTest = new RadioButton();
		MobileTest.setText("Test Mobilny");
		MobileTest.setTranslateX(100);
		MobileTest.setTranslateY(-220);
		MobileTest.setToggleGroup(typeOfTestGroup);

		MobileTest.setDisable(true);// ZASLEPKA

		// toDo Jak w GUI zaznaczy przegl¹darke której nie ma na kompie to nie robi
		// testu tylko wyrzuca komunikat
		final ToggleGroup browsersGroup = new ToggleGroup();
		// RadioButton Firefox
		Firefox = new RadioButton();
		Firefox.setTranslateX(-220);
		Firefox.setTranslateY(-140);
		Firefox.setSelected(true);
		Firefox.setToggleGroup(browsersGroup);
		// Image Firefox
		Image FirefoxImage = new javafx.scene.image.Image(getClass().getResourceAsStream("firefox.png"));
		ImageView imgViewFirefox = new ImageView(FirefoxImage);
		imgViewFirefox.setTranslateX(-220);
		imgViewFirefox.setTranslateY(-180);

		// RadioButton Chrome
		Chrome = new RadioButton();
		Chrome.setTranslateX(-110);
		Chrome.setTranslateY(-140);
		Chrome.setToggleGroup(browsersGroup);
		// Image Chrome
		Image ChromeImage = new javafx.scene.image.Image(getClass().getResourceAsStream("chrome.png"));
		ImageView imgViewChrome = new ImageView(ChromeImage);
		imgViewChrome.setTranslateX(-110);
		imgViewChrome.setTranslateY(-180);

		// RadioButton Opera
		Opera = new RadioButton();
		Opera.setTranslateX(0);
		Opera.setTranslateY(-140);
		Opera.setToggleGroup(browsersGroup);
		// Image Opera
		Image OperaImage = new javafx.scene.image.Image(getClass().getResourceAsStream("opera.png"));
		ImageView imgViewOpera = new ImageView(OperaImage);
		imgViewOpera.setTranslateX(0);
		imgViewOpera.setTranslateY(-180);

		// RadioButton IE
		IE = new RadioButton();
		IE.setTranslateX(110);
		IE.setTranslateY(-140);
		IE.setToggleGroup(browsersGroup);
		// Image IE
		Image IEImage = new javafx.scene.image.Image(getClass().getResourceAsStream("IE.png"));
		ImageView imgViewIE = new ImageView(IEImage);
		imgViewIE.setTranslateX(110);
		imgViewIE.setTranslateY(-180);

		// RadioButton Edge
		Edge = new RadioButton();
		Edge.setTranslateX(220);
		Edge.setTranslateY(-140);
		Edge.setToggleGroup(browsersGroup);
		// Image IE
		Image EdgeImage = new javafx.scene.image.Image(getClass().getResourceAsStream("Edge.png"));
		ImageView imgViewEdge = new ImageView(EdgeImage);
		imgViewEdge.setTranslateX(220);
		imgViewEdge.setTranslateY(-180);

		// TableView with select the Continents with Currency
		// Add Continents to list
		list= FXCollections.observableArrayList();
		list.add(new ObjectToTableView(false, "Afryka","Africa", tc.CurrencyOfAfrica));
		list.add(new ObjectToTableView(false, "Ameryka Po³udniowa","SouthAmerica", tc.CurrencyOfSouthAmerica));
		list.add(new ObjectToTableView(false, "Ameryka Pó³nocna","NorthAmerica", tc.CurrencyOfNorthAmerica));
		list.add(new ObjectToTableView(false, "Australia","Australia", tc.CurrencyOfAustralia));
		list.add(new ObjectToTableView(false, "Azja","Asia", tc.CurrencyOfAsia));
		list.add(new ObjectToTableView(false, "Europa","Europe", tc.CurrencyOfEurope));
		// new table
		table = new TableView<ObjectToTableView>();
		table.setEditable(true);
		table.setItems(list);
		// Create and add column1 with CheckBox
		TableColumn<ObjectToTableView, Boolean> Column1 = new TableColumn<ObjectToTableView, Boolean>("Testowanie");
		Column1.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, Boolean>("run"));
		Column1.setCellFactory(column -> new CheckBoxTableCell());
		table.getColumns().add(Column1);
		// Create and add column2 with Continent name
		TableColumn<ObjectToTableView, String> Column2 = new TableColumn<ObjectToTableView, String>("Nazwa kontynentu");
		Column2.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, String>("ContinentName"));
		table.getColumns().add(Column2);
		// Create and add column3 with currency count
		TableColumn<ObjectToTableView, Integer> Column3 = new TableColumn<ObjectToTableView, Integer>("Liczba walut");
		Column3.setCellValueFactory(new PropertyValueFactory<ObjectToTableView, Integer>("CurrencyCountContinent"));
		table.getColumns().add(Column3);
		// Set size tableView
		double Xsize = 296;
		double Ysize = 180;
		table.setMinWidth(Xsize);
		table.setMaxWidth(Xsize);
		table.setMinHeight(Ysize);
		table.setMaxHeight(Ysize);
		// Set position tableView
		table.setTranslateY(-20);
		table.setTranslateX(-130);

		final ToggleGroup allOrChosenCurrencyGroup = new ToggleGroup();
		All = new RadioButton();
		All.setText("Wszystkie waluty (Iloœæ: " + tc.CurrencyAll.size() + ")");
		All.setTranslateX(125);
		All.setTranslateY(-65);
		All.setToggleGroup(allOrChosenCurrencyGroup);
		// Event after click All Currency. CheckBoxes in tableView unchecked
		All.setOnAction(e -> {
			try {
				logi.addToLogs();
				logi.addToLogs("Kliknieto Wszystkie Waluty (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 224);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		Chosen = new RadioButton();
		Chosen.setText("Wybrane waluty z tabeli kontynentów");
		Chosen.setTranslateX(150);
		Chosen.setTranslateY(-25);
		Chosen.setToggleGroup(allOrChosenCurrencyGroup);
		Chosen.setSelected(true);
		Chosen.setOnAction(e -> {
			table.setEditable(true);
			try {
				logi.addToLogs();
				logi.addToLogs("Klikniêto wybrane waluty, tableView aktywne (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 249);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (ObjectToTableView item : list) {
				item.SetRunProperty(false);
			}
			table.refresh();
		});

		
		//CheckBox gold test
	
		goldTest.setText("Testowanie kursu z³ota");
		goldTest.setTranslateX(112);
		goldTest.setTranslateY(25);
		
		// PROGRESS BAR
		progress.setText("Progress:");
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

		progressCurrencyInfo.setText("Uruchamianie testu...");
		progressCurrencyInfo.setTranslateX(0);
		progressCurrencyInfo.setTranslateY(175);
		progressCurrencyInfo.setFont(Font.font("Arial", 18));

		 pb.setVisible(false);
		 pi.setVisible(false);
		 progress.setVisible(false);
		 progressCurrencyInfo.setVisible(false);
		
		// Button Start
		Start = new Button();
		Start.setTranslateX(0);
		Start.setTranslateY(220);
		Start.setText("Start");
		// Change font for button Start
		Start.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		Start.setOnAction(e -> {
			try {
				progressCurrencyInfo.setText("Uruchamianie testu...");
				logi.addToLogs();
				logi.addToLogs("Kliknieto start (UserGUI)", getClass().getName().toString(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), 144);
				logi.addToLogs();
				// Preparing lists from CSV
				preparingCSVandLists();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			threadAnalysis = new Thread(new Analysis());
			threadAnalysis.start();
		});
		// Add elements to root (layout)
		root = new StackPane();
		root.getChildren().add(Start);
		root.getChildren().add(WebTest);
		root.getChildren().add(MobileTest);
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
		root.getChildren().add(All);
		root.getChildren().add(Chosen);
		root.getChildren().add(progress);
		root.getChildren().add(pb);
		root.getChildren().add(pi);
		root.getChildren().add(progressCurrencyInfo);
		root.getChildren().add(goldTest);
		// Window
		Scene scene = new Scene(root, 600, 500);
		// Event on click close (X)
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
		javafx.scene.image.Image icon = new javafx.scene.image.Image(getClass().getResourceAsStream("icon.png"));
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
		if (tc.CurrencyOfEurope == null || tc.CurrencyOfAsia == null || tc.CurrencyOfAustralia == null
				|| tc.CurrencyOfNorthAmerica == null || tc.CurrencyOfSouthAmerica == null
				|| tc.CurrencyOfAfrica == null) {
			tc.CurrencyOfEurope = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencyOfAsia = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencyOfAustralia = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencyOfNorthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencyOfSouthAmerica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencyOfAfrica = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencyAll = new ArrayList<ObjectAllAboutCurrencyCSV>();
			tc.CurrencySelected = new ArrayList<ObjectAllAboutCurrencyCSV>();
			pCSV.readCSVDate();
			tc.CurrencyOfEurope = pCSV.CSVCurrencyOfEurope;
			tc.CurrencyOfAsia = pCSV.CSVCurrencyOfAsia;
			tc.CurrencyOfAustralia = pCSV.CSVCurrencyOfAustralia;
			tc.CurrencyOfNorthAmerica = pCSV.CSVCurrencyOfNorthAmerica;
			tc.CurrencyOfSouthAmerica = pCSV.CSVCurrencyOfSouthAmerica;
			tc.CurrencyOfAfrica = pCSV.CSVCurrencyOfAfrica;


			// add all lists to CountriesAll list
			tc.CurrencyAll.addAll(tc.CurrencyOfEurope);
			tc.CurrencyAll.addAll(tc.CurrencyOfAsia);
			tc.CurrencyAll.addAll(tc.CurrencyOfAustralia);
			tc.CurrencyAll.addAll(tc.CurrencyOfNorthAmerica);
			tc.CurrencyAll.addAll(tc.CurrencyOfSouthAmerica);
			tc.CurrencyAll.addAll(tc.CurrencyOfAfrica);
		}
	}

}