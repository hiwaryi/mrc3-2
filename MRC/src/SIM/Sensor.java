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

    public Colorblob(boolean front, boolean back, boolean left, boolean right){
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
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
    private int direction;
    private Position dir;
    private final int FRONT = 1;
    private final int RIGHT = 2;
    private final int BACK = 3;
    private final int LEFT = 4;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 1;
    }

    public Position(){
        this.x = 0;
        this.y = 0;
        this.direction = 1;
    }

    public void setDirection(int direction) {
        if(1 <= direction && direction <= 4)
            this.direction = direction;
    }

    public Position front(){
        Position myFront = new Position();

        if(direction == FRONT)
            myFront.setPosition(this.x, this.y + 1);
        else if(direction == BACK)
            myFront.setPosition(this.x, this.y - 1);
        else if(direction == LEFT)
            myFront.setPosition(this.x - 1, this.y);
        else if(direction == RIGHT)
            myFront.setPosition(this.x + 1, this.y);

        return myFront;
    }

    public Position left(){
        Position myLeft = newPosition();

        if(direction == FRONT)
            myLeft.setPosition(this.x - 1, this.y);
        else if(direction == BACK)
            myLeft.setPosition(this.x + 1, this.y);
        else if(direction == LEFT)
            myLeft.setPosition(this.x, this.y - 1);
        else if(direction == RIGHT)
            myLeft.setPosition(this.x, this.y + 1);
    }

    public Position right(){
        Position myRight = newPosition();

        if(direction == FRONT)
            myRight.setPosition(this.x + 1, this.y);
        else if(direction == BACK)
            myRight.setPosition(this.x - 1, this.y);
        else if(direction == LEFT)
            myRight.setPosition(this.x, this.y + 1);
        else if(direction == RIGHT)
            myRight.setPosition(this.x, this.y - 1);
    }

    public Position back(){
        Position myBack = newPosition();

        if(direction == FRONT)
            myBack.setPosition(this.x, this.y - 1);
        else if(direction == BACK)
            myBack.setPosition(this.x, this.y + 1);
        else if(direction == LEFT)
            myBack.setPosition(this.x + 1, this.y);
        else if(direction == RIGHT)
            myBack.setPosition(this.x - 1, this.y);
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

    public void setPosition(Position pos){
        int x = pos.getX();
        int y = pos.getY();

        this.x = x;
        this.y = y;
    }
}

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
