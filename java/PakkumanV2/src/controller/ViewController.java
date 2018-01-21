package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import core.Main;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private GridPane menuGrid;

    private Stage primaryStage;

    public ViewController (Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setScene() {}

    public void toGameView() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuGrid.setPrefHeight(Main.height);
        menuGrid.setPrefWidth(Main.width);
    }
}
