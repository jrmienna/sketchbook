package ov1.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        Font.loadFont(Calculator.class.getResource("Helvetica Neue CE 35 This.ttf").toExternalForm(), 10);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, Color.BLACK));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
