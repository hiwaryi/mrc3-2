package SIM;

public class Sensor {
    private boolean hazard;
    private Colorblob cb;
    private Position pos;

    public Sensor(boolean hazard, Colorblob cb, Position pos) {
        this.hazard = hazard;
        this.cb = cb;
        this.pos = pos;
    }

    public boolean isHazard() {
        return hazard;
    }

    public Colorblob getCb() {
        return cb;
    }

    public Position getPos() {
        return pos;
    }
}
