package SIM;

public class Sensor {
    private boolean hazard;
    private Colorblob cb;
    private Position pos;


    public Sensor() {
        this.hazard = false;
        this.cb = new Colorblob();
        this.pos = new Position(0, 0);
    }

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
