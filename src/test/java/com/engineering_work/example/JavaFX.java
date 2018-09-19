package com.engineering_work.example;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.text.Element;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.event.ActionEvent;


import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.event.WindowEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

public class JavaFX extends Application {
	TestControl tc = new TestControl();
	
	Thread threadAnalysis = new Thread(new Analysis());

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

	// Button Start
	public static Button Start;

	@Override
	public void start(Stage primaryStage) throws Exception {

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

		MobileTest.setDisable(true);

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

		Start = new Button();
		Start.setTranslateX(0);
		Start.setTranslateY(220);
		Start.setText("Start");
		Start.setOnAction(e ->{ threadAnalysis.start();});


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

		Scene scene = new Scene(root, 600, 500);

		primaryStage.setOnCloseRequest(event -> {
			try {
				tc.driver.quit();
			} catch (Exception e) {
			}
			System.exit(0);
		});

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
}