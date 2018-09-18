package com.engineering_work.example;




import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import java.io.IOException;
import javafx.scene.control.ToggleGroup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

 
public class JavaFX extends Application {
    TestControl tc= new TestControl(); 

    RadioButton rb1;
    RadioButton rb2;
    Button Start;
	@Override
    public void start(Stage primaryStage) {
		
		final ToggleGroup group = new ToggleGroup();
		  rb1= new RadioButton();
		  rb1.setText("Test Webowy");
		  rb1.setTranslateX(-100);
		  rb1.setTranslateY(-220);
		  rb1.setSelected(true);
		  rb1.setToggleGroup(group);
		  
		  rb2= new RadioButton();
		  rb2.setText("Test Mobilny");
		  rb2.setTranslateX(100);
		  rb2.setTranslateY(-220);
		  rb2.setToggleGroup(group);
		
		/*CheckBox WebTestCB= new CheckBox();
		  WebTestCB.setText("Test Webowy");
		  WebTestCB.setTranslateX(200);
		  WebTestCB.setTranslateX(200);

		  CheckBox MobileTestCB= new CheckBox();
		  MobileTestCB.setText("Test Mobilny");
		  MobileTestCB.setTranslateX(100);
		  MobileTestCB.setTranslateY(100);*/
		  
		Start = new Button();
		Start.setTranslateX(0);
		Start.setTranslateY(220);
        Start.setText("Start");
        Start.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	analysis();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(Start);
        root.getChildren().add(rb1);
        root.getChildren().add(rb2);
        Scene scene = new Scene(root, 500, 500);
        
    	javafx.scene.image.Image icon=new javafx.scene.image.Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(icon);
        
        primaryStage.setTitle("NBP API Tests");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
 	public void analysis() {
	 
 		
 		if (rb1.isSelected())
 		{
 		 try {
				tc.beforeTest();
				tc.TestWeb();
				tc.afterTest();
			} catch (IOException e) {
				e.printStackTrace();
			}
 		}
 		else
 		{
 			 try {
			tc.beforeTest();
			tc.TestMobile();
			tc.afterTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		}
 }
}