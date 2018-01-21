package sample;

/**
 * Created by mienna on 29/05/15.
 */
public class LightPoint {

    private final String lightId;
    private final int numberOfLightLevels;
    private boolean on;
    private int lightLevel;


    protected LightPoint(String lightId, boolean dimable, int numberOfLightLevels) {
        this.lightId = lightId;
        this.numberOfLightLevels = numberOfLightLevels;
        this.lightLevel = 0;
        this.on = false;
    }

    public String getLightId() {
        return lightId;
    }

    public boolean isOn() {
        return on;
    }

    public void toggleOn() {
        this.on = !this.on;
        System.out.println("" + this.lightId + ": " + this.on);
    }

    public int getNumberOfLightLevels() {
        return this.numberOfLightLevels;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
        if(lightLevel > 0) {
            this.on = true;
        } else {
            this.on = false;
        }

        System.out.println("" + this.lightId + ": " + this.lightLevel);
    }
}
