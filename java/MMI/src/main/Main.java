package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String MENU_SCREEN = "menu";
    public static final String OVING1_SCREEN = "oving1";
    public static final String WORDFEUD_SCREEN = "ov1/wordfeud";


    @Override
    public void start(Stage stage) throws Exception{

        ScreenStack screenStack = new ScreenStack(stage);

        // put all the screens in the stack
        screenStack.loadScreen(Main.MENU_SCREEN, Main.MENU_SCREEN + ".fxml");
        screenStack.loadScreen(Main.OVING1_SCREEN, Main.OVING1_SCREEN + ".fxml");
        screenStack.loadScreen(Main.WORDFEUD_SCREEN, Main.WORDFEUD_SCREEN + ".fxml");

        // set the menu screen on top of stack
        screenStack.setScreen(Main.MENU_SCREEN);

        Scene scene = new Scene(screenStack);

        stage.setScene(scene);
        stage.setTitle("MMI");
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
