package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by mienna on 13/01/15.
 */
public class ScreenStack extends BorderPane {

    // Holds all the screens to be displayed
    private HashMap<String, Node> screens = new HashMap<String, Node>();

    // Needs access to the stage, so it can set size properties to fit screens
    private Stage stage;

    public ScreenStack(Stage stage) {
        this.stage = stage;
        //setTop(new ToolBar(new Button("Back")));
    }

    // Add screen to the hashmap
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    // Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }

    // Load fxml file specified by resources, return true if successful
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) fxmlLoader.load();
            ControlledScreen screen = ((ControlledScreen) fxmlLoader.getController());
            screen.setScreenParent(this);
            addScreen(name, loadScreen);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Displays the screen with the given name
    public boolean setScreen(final String name) {
        if (screens.get(name) != null){
            final DoubleProperty opacity = opacityProperty();

            if(!this.getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(300), new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        setCenter(screens.get(name));
                                        Timeline fadeIn = new Timeline(
                                                new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(opacity,0.0)),
                                                new KeyFrame(new Duration(300), new javafx.animation.KeyValue
                                                        (opacity,1.0)));
                                        fadeIn.play();
                                    }
                                }, new javafx.animation.KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                setCenter(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(800), new javafx.animation.KeyValue(opacity, 1.0)));
                fadeIn.play();
            }

            /*
            if(stage.getScene() != null) {
                stage.setHeight(((Region) screens.get(name)).getMinHeight());
                stage.setWidth(((Region) screens.get(name)).getMinWidth());
            }
            */

            stage.sizeToScene();

            return true;
        }
        return false;
    }

    public boolean unloadScreen(String name) {
        return false;
    }
}