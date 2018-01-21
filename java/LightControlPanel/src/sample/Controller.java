package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private LightModel lightSystem;

    @FXML
    private Button LP1Button, LP2Button, LP3Button, LP4Button;

    @FXML
    private Slider LP1Slider, LP2Slider, LP3Slider, LP4Slider;

    @FXML
    private Label LP1Label, LP2Label, LP3Label, LP4Label;


    private void initModel() {
        lightSystem = new LightModel();
    }

    private void initGUI() {
        LP1Slider.setMax(lightSystem.getNumberOfLightLevels("LP1"));
        LP2Slider.setMax(lightSystem.getNumberOfLightLevels("LP2"));
        LP3Slider.setMax(lightSystem.getNumberOfLightLevels("LP3"));
        LP4Slider.setMax(lightSystem.getNumberOfLightLevels("LP4"));

        LP1Label.textProperty().bind(LP1Slider.valueProperty().asString());
        LP2Label.textProperty().bind(LP2Slider.valueProperty().asString());
        LP3Label.textProperty().bind(LP3Slider.valueProperty().asString());
        LP4Label.textProperty().bind(LP4Slider.valueProperty().asString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initModel();
        initGUI();
    }

    public void handleButtonClick(Event event) {
        if(event.getSource() == LP1Button) {
            lightSystem.toggleOn("LP1");
            if(lightSystem.getLightPoint("LP1").isOn()) {
                LP1Button.setText("ON");
            } else {
                LP1Button.setText("OFF");
            }
        }
        if(event.getSource() == LP2Button) {
            lightSystem.toggleOn("LP2");
            if(lightSystem.getLightPoint("LP2").isOn()) {
                LP2Button.setText("ON");
            } else {
                LP2Button.setText("OFF");
            }
        }
        if(event.getSource() == LP3Button) {
            lightSystem.toggleOn("LP3");
            if(lightSystem.getLightPoint("LP3").isOn()) {
                LP3Button.setText("ON");
            } else {
                LP3Button.setText("OFF");
            }
        }
        if(event.getSource() == LP4Button) {
            lightSystem.toggleOn("LP4");
            if(lightSystem.getLightPoint("LP4").isOn()) {
                LP4Button.setText("ON");
            } else {
                LP4Button.setText("OFF");
            }
        }
    }

    public void handleSliderDrag(Event event) {
        if(event.getSource() == LP1Slider) {
            lightSystem.setLightLevel("LP1", (int) LP1Slider.getValue());
        }
        if(event.getSource() == LP2Slider) {
            lightSystem.setLightLevel("LP2", (int) LP2Slider.getValue());
        }
        if(event.getSource() == LP3Slider) {
            lightSystem.setLightLevel("LP3", (int) LP3Slider.getValue());
        }
        if(event.getSource() == LP4Slider) {
            lightSystem.setLightLevel("LP4", (int) LP4Slider.getValue());
        }
    }

}
