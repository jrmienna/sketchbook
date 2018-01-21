package main;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mienna on 13/01/15.
 */
public class Oving1ScreenController implements Initializable, ControlledScreen {

    ScreenStack screenStack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreenStack screenParent) {
        this.screenStack = screenParent;
    }


    public void goToWordFeud(ActionEvent actionEvent) {
        screenStack.setScreen(Main.WORDFEUD_SCREEN);
    }

    public void goToStickmanRunner(ActionEvent actionEvent) {

    }

    public void goToTimer(ActionEvent actionEvent) {

    }
}
