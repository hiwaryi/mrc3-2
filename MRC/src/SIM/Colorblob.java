package SIM;

/**
 * Created by WarYi on 15. 11. 29..
 */
public class Colorblob {
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
