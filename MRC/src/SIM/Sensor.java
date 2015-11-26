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
    private string direction;
    private Position dir;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = new string("UP");
    }

    public Position(){
        this.x = 0;
        this.y = 0;
        this.direction = new string("UP");
    }

    public void setDirection(string direction) {
        if(direction.equals("UP") || direction.equals("DOWN") || direction.equals("LEFT") || direction.equals("RIGHT"))
            this.direction = direction;
    }

    public Position front(){
        Position myFront = new Position();

        if(this.direction.equals("UP"))
            myFront.setPosition(this.x, this.y + 1);
        else if(this.direction.equals("DOWN"))
            myFront.setPosition(this.x, this.y - 1);
        else if(this.direction.equals("LEFT"))
            myFront.setPosition(this.x - 1, this.y);
        else if(this.direction.equals("RIGHT"))
            myFront.setPosition(this.x + 1, this.y);

        return myFront;
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

    public void setPosition(int x, int y){
        this.x = x;
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
