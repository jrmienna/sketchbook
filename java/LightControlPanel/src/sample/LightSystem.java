package sample;

import sample.LightListener;

// controls a set of lights indicated by the String identifiers (lightId) “LP1”-“LP4”
public interface LightSystem {

    // returns the number of light levels/states the light with the given id supports
    public int getNumberOfLightLevels(String lightId);

    // returns the current level/state of the light with the given id (0 means off)
    public int getLightLevel(String lightId);

    // sets the current level/state of the light with the given id (0 means off)
    public void setLightLevel(String lightId, int level);

    public void toggleOn(String lightId);

    // adds a listener that will be notified when one of the lights changes state
    public void addLightListener(LightListener lightListener);

    // removes a previously registered listener
    public void removeLightListener(LightListener lightListener);

    // checks if light level has changed and notifies listeners of the change
    public void notifyLightListeners();
}