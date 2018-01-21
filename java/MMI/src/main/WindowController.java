package main;

import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Created by mienna on 19/01/15.
 */
public class WindowController {

    private HashMap<String, Node> nodes;
    private Stage stage;

    public WindowController(Stage primaryStage, Node initialScreen) {
        this.nodes = new HashMap<String, Node>();
        this.stage = primaryStage;
        this.setScene();
    }

    private void setScene() {

    }

}
