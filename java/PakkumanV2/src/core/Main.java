package core;

import controller.ViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static int width = 1024, height = 800;

    private String MENU_VIEW = "/view/menu.fxml";
    private String GAME_VIEW = "/view/game.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{

        final Game game = new Game();

        Parent root = FXMLLoader.load(getClass().getResource(MENU_VIEW));

        ViewController viewController = new ViewController(primaryStage);
        viewController.setScene();


        primaryStage.setTitle("Pakkuman V2");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                game.init();
                game.run();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
