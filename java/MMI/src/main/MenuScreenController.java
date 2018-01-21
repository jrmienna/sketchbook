package main;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuScreenController implements Initializable, ControlledScreen{

    ScreenStack screenStack;

    private double screenWidth = 300;
    private double screenHeight = 500;

    @Override
    public void setScreenParent(ScreenStack screenStack) {
        this.screenStack = screenStack;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToOving1(ActionEvent actionEvent) {
        screenStack.setScreen(Main.OVING1_SCREEN);
    }

    public void goToOving2(ActionEvent actionEvent) {}

    public void goToOving3(ActionEvent actionEvent) {}
}
