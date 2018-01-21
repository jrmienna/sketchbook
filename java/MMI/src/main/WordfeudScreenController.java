package main;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mienna on 14/01/15.
 */
public class WordfeudScreenController implements Initializable, ControlledScreen {

    ScreenStack screenStack;

    @Override
    public void setScreenParent(ScreenStack screenStack) {
        this.screenStack = screenStack;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
