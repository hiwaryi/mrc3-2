package SIM;

class Colorblob{
    private boolean front;
    private boolean back;
    private boolean left;
    private boolean right;

    public Colorblob(){
        this.front = false;
        this.back = false;
        this.left = false;
        this.right = false;
    }

    public boolean getFront(){
        return this.front;
    }

    public boolean getBack(){
        return this.back;
    }

    public boolean getLeft(){
        return this.left;
    }

    public boolean getRight(){
        return this.right;
    }

    public void setFront(boolean front){
        this.front = front;
    }

    public void setBack(boolean back){
        this.back = back;
    }

    public void setLeft(boolean left){
        this.left = left;
    }

    public void setRight(boolean right){
        this.right = right;
    }
}

class Position{
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Sensor {
    private boolean hazard;
    private Colorblob cb;
    private Position pos;


    public Sensor() {
        hazard = false;
        cb = new Colorblob();
        pos = new Position(0, 0);
    }

    public boolean isHazard() {
        return hazard;
    }

    public void setHazard(boolean hazard) {
        this.hazard = hazard;
    }

    public Colorblob getCb() {
        return cb;
    }

    public void setCb(Colorblob cb) {
        this.cb = cb;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
