package ov1.runtracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Runtracker extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("runtracker.fxml"));
        primaryStage.setTitle("Runtracker");
        primaryStage.setScene(new Scene(root, Color.WHITE));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
