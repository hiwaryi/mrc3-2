package SIM;

public class sim {
    private boolean state;
    private int xPos, yPos;
    private int nextDirection, nowDirection;
    private final int FRONT = 1;
    private final int RIGHT = 2;
    private final int BACK = 3;
    private final int LEFT = 4;

    private Sensor sensor;

    public sim(){
        sensor = new Sensor();
        state = false;
        nextDirection = -1;
        nowDirection = FRONT;
        sensor.isHazard();
        //ADD_ON.EnterSensorData(sensor.isHazard());
    }


    public void moveInterface() {
        if(nextDirection!=-1) {
            state = true;
            switch (nextDirection){
                case 1:
                    if(nowDirection==FRONT)
                        yPos++;
                    else if(nowDirection==RIGHT)
                        xPos++;
                    else if(nowDirection==BACK)
                        yPos--;
                    else if(nowDirection==LEFT)
                        xPos--;
                    else
                    break;
                case 2:
                    if(nowDirection==FRONT)
                        nowDirection = RIGHT;
                    else if(nowDirection==RIGHT)
                        nowDirection = BACK;
                    else if(nowDirection==BACK)
                        nowDirection = LEFT;
                    else if(nowDirection==LEFT)
                        nowDirection = FRONT;
                    else
                    break;
                case 3:
                    if(nowDirection==FRONT)
                        nowDirection = LEFT;
                    else if(nowDirection==RIGHT)
                        nowDirection = FRONT;
                    else if(nowDirection==BACK)
                        nowDirection = RIGHT;
                    else if(nowDirection==LEFT)
                        nowDirection = BACK;
                    else
                    break;
                default://err
                    break;
            }
            state = false;
        }
    }

    public void setNextStep(int next){
        // 1: move Front
        // 2: turn right;
        // 3: turn left;
        nextDirection = next;
    }

    public void forceStop() {
        state = false;
    }

    public boolean getState() {
        return state;
    }
}