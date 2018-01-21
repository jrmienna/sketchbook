package sample;

import javafx.scene.effect.Light;

import java.util.ArrayList;

/**
 * Created by mienna on 26/05/15.
 */
public class LightModel implements LightSystem {

    ArrayList<LightPoint> lights = new ArrayList<LightPoint>();
    ArrayList<LightListener> listeners = new ArrayList<LightListener>();

    public LightModel() {
        LightPoint LP1 = new LightPoint("LP1", true, 100);
        LightPoint LP2 = new LightPoint("LP2", true, 100);
        LightPoint LP3 = new LightPoint("LP3", false, 0);
        LightPoint LP4 = new LightPoint("LP4", true, 100);

        lights.add(LP1);
        lights.add(LP2);
        lights.add(LP3);
        lights.add(LP4);
    }

    public LightPoint getLightPoint(String lightId) {
        for(LightPoint light : lights) {
           if(light.getLightId().equals(lightId)) {
                return light;
           }
        }
        return null;
    }

    @Override
    public int getNumberOfLightLevels(String lightId) {
        return this.getLightPoint(lightId).getNumberOfLightLevels();
    }

    @Override
    public int getLightLevel(String lightId) {
        return this.getLightPoint(lightId).getLightLevel();
    }

    @Override
    public void setLightLevel(String lightId, int level) {
        this.getLightPoint(lightId).setLightLevel(level);
    }

    @Override
    public void toggleOn(String lightId) {
        this.getLightPoint(lightId).toggleOn();
    }

    @Override
    public void addLightListener(LightListener lightListener) {
        this.listeners.add(lightListener);
    }

    @Override
    public void removeLightListener(LightListener lightListener) {
        this.listeners.remove(lightListener);
    }

    @Override
    public void notifyLightListeners() {
        for(LightListener listener : listeners) {
            listener.update();
        }
    }
}
