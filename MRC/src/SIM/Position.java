package SIM;

/**
 * Created by WarYi on 15. 11. 29..
 */
public class Position {
    private int x;
    private int y;
    private int direction;
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
        Position myLeft = new Position();

        if(direction == FRONT)
            myLeft.setPosition(this.x - 1, this.y);
        else if(direction == BACK)
            myLeft.setPosition(this.x + 1, this.y);
        else if(direction == LEFT)
            myLeft.setPosition(this.x, this.y - 1);
        else if(direction == RIGHT)
            myLeft.setPosition(this.x, this.y + 1);

        return myLeft;
    }

    public Position right(){
        Position myRight = new Position();

        if(direction == FRONT)
            myRight.setPosition(this.x + 1, this.y);
        else if(direction == BACK)
            myRight.setPosition(this.x - 1, this.y);
        else if(direction == LEFT)
            myRight.setPosition(this.x, this.y + 1);
        else if(direction == RIGHT)
            myRight.setPosition(this.x, this.y - 1);

        return myRight;
    }

    public Position back(){
        Position myBack = new Position();

        if(direction == FRONT)
            myBack.setPosition(this.x, this.y - 1);
        else if(direction == BACK)
            myBack.setPosition(this.x, this.y + 1);
        else if(direction == LEFT)
            myBack.setPosition(this.x + 1, this.y);
        else if(direction == RIGHT)
            myBack.setPosition(this.x - 1, this.y);

        return myBack;
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

    public int getDirection(){
        return direction;
    }

    @Override
    public boolean equals(Object in){
        int x = ((Position)in).getX();
        int y = ((Position)in).getY();

        if(this.getX() == x && this.getY() == y)
            return true;
        else
            return false;
    }
}
