package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by mienna on 29/05/15.
 */
public class LightPointWrapper implements LightListener {

    private LightPoint lp;

    private BooleanProperty onProperty;
    private StringProperty ligthIdProperty;
    private IntegerProperty numberOfLightLevelsProperty;

    public LightPointWrapper(LightPoint lp) {
        this.lp = lp;
        this.onProperty.setValue(lp.isOn());
        this.ligthIdProperty.setValue(lp.getLightId());
        this.numberOfLightLevelsProperty.setValue(lp.getNumberOfLightLevels());
    }

    public String getLigthId() {
        return this.ligthIdProperty.get();
    }

    public StringProperty ligthIdProperty() {
        return this.ligthIdProperty;
    }

    public int getNumberOfLightLevels() {
        return numberOfLightLevelsProperty.get();
    }

    public IntegerProperty numberOfLightLevelsProperty() {
        return numberOfLightLevelsProperty;
    }

    public boolean getOnProperty() {
        return onProperty.get();
    }

    public BooleanProperty onProperty() {
        return onProperty;
    }

    @Override
    public void update() {
        this.onProperty.setValue(lp.isOn());
        this.ligthIdProperty.setValue(lp.getLightId());
        this.numberOfLightLevelsProperty.setValue(lp.getNumberOfLightLevels());
    }
}
