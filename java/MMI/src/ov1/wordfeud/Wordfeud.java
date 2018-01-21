package ov1.wordfeud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Wordfeud extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("wordfeud.fxml"));
        primaryStage.setTitle("Wordfeud");
        primaryStage.setScene(new Scene(root, Color.BLACK));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
