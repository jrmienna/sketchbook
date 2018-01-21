package ov1.timer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Timer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();


        String[] alph = {" ", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

        // BOTTOM
        GridPane buttonGrid = new GridPane();
        int counter = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("" + counter);
                //button.setText("" + counter + "\n" + alph[counter-1]);
                button.getStyleClass().add("btn");
                GridPane.setRowIndex(button, i);
                GridPane.setColumnIndex(button, j);
                counter ++;
                buttonGrid.getChildren().add(button);
            }

        }


        Button buttonEmpty1 = new Button(" ");
        GridPane.setColumnIndex(buttonEmpty1, 0);
        GridPane.setRowIndex(buttonEmpty1, 3);
        buttonEmpty1.getStyleClass().add("btn-empty");

        Button buttonEmpty2 = new Button(" ");
        GridPane.setColumnIndex(buttonEmpty2, 2);
        GridPane.setRowIndex(buttonEmpty2, 3);
        buttonEmpty2.getStyleClass().add("btn-empty");

        Button button0 = new Button("" + 0);
        GridPane.setColumnIndex(button0, 1);
        GridPane.setRowIndex(button0, 3);
        buttonGrid.getChildren().addAll(buttonEmpty1, buttonEmpty2, button0);
        button0.getStyleClass().add("btn");

        root.setBottom(buttonGrid);


        // CENTER

        HBox buttonContainer = new HBox(20);
        buttonContainer.setAlignment(Pos.BASELINE_CENTER);
        Button cancel = new Button("Cancel");
        Button set = new Button("Set");

        buttonContainer.getStyleClass().add("center-wrapper");
        cancel.getStyleClass().add("btn-cancel");
        set.getStyleClass().add("btn-set");

        buttonContainer.getChildren().addAll(cancel, set);
        root.setCenter(buttonContainer);


        // TOP
        GridPane numbContainer = new GridPane();
        numbContainer.setPadding(new Insets(10));
        numbContainer.setAlignment(Pos.BASELINE_CENTER);
        numbContainer.getStyleClass().add("numb-container");

        Text h = new Text("Hours");
        Text m = new Text("Minutes");
        Text s = new Text("Seconds");

        h.setTextAlignment(TextAlignment.CENTER);
        m.setTextAlignment(TextAlignment.CENTER);
        s.setTextAlignment(TextAlignment.CENTER);

        GridPane.setColumnIndex(h, 0);
        GridPane.setRowIndex(h, 0);

        GridPane.setColumnIndex(m, 2);
        GridPane.setRowIndex(m, 0);

        GridPane.setColumnIndex(s, 4);
        GridPane.setRowIndex(s, 0);

        h.getStyleClass().add("numb-txt");
        m.getStyleClass().add("numb-txt");
        s.getStyleClass().add("numb-txt");

        Label hours = new Label("0");
        Label div1 = new Label(":");
        Label minutes = new Label("00");
        Label div2 = new Label(":");
        Label seconds = new Label("00");

        numbContainer.getChildren().addAll(h, m, s);

        GridPane.setColumnIndex(hours, 0);
        GridPane.setRowIndex(hours, 1);

        GridPane.setColumnIndex(div1, 1);
        GridPane.setRowIndex(div1, 1);

        GridPane.setColumnIndex(minutes, 2);
        GridPane.setRowIndex(minutes, 1);

        GridPane.setColumnIndex(div2, 3);
        GridPane.setRowIndex(div2, 1);

        GridPane.setColumnIndex(seconds, 4);
        GridPane.setRowIndex(seconds, 1);

        hours.getStyleClass().add("numbs");
        div1.getStyleClass().add("numbs");
        minutes.getStyleClass().add("numbs");
        div2.getStyleClass().add("numbs");
        seconds.getStyleClass().add("numbs");
        numbContainer.getChildren().addAll(hours, div1, minutes, div2, seconds);

        root.setTop(numbContainer);




        Scene scene = new Scene(root, Color.BLACK);
        scene.getStylesheets().add("ov1/timer/Timer.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
