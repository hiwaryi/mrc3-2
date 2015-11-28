package SIM;

public class sim {
    private boolean state;
    private int xPos, yPos;
    private int nextStep, nowDirection;
    private final int FRONT = 1;
    private final int RIGHT = 2;
    private final int BACK = 3;
    private final int LEFT = 4;

    private Sensor sensor;
    private Position position;

    public sim(){
        sensor = new Sensor();
        position = sensor.getPos();
        state = false;
        nextStep = -1;
        sensor.isHazard();
        //ADD_ON.EnterSensorData(sensor.isHazard());
    }

    public void moveInterface() {
        // 실제로 움직임
        if(nextStep!=-1) {
            state = true;
            xPos = position.getX();
            yPos = position.getY();
            nowDirection = position.getDirection();
            switch (nextStep){
                case 1://move to front
                    switch (nowDirection){
                        case FRONT:
                            position.setPosition(xPos, yPos++);
                            break;
                        case RIGHT:
                            position.setPosition(xPos++, yPos);
                            break;
                        case BACK:
                            position.setPosition(xPos, yPos--);
                            break;
                        case LEFT:
                            position.setPosition(xPos--, yPos);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2://turn right
                    nowDirection+=1;
                    nowDirection%=4;
                    position.setDirection(nowDirection);
                    break;
                case 3://turn left
                    nowDirection-=1;
                    nowDirection%=4;
                    position.setDirection(nowDirection);
                    break;
                default:
                    break;
            }
            state = false;
        }
    }

    public void setNextStep(int next){
        //ADD-ON한테 명령 받음
        // 1: move Front
        // 2: turn right;
        // 3: turn left;
        nextStep = next;
        moveInterface();
    }

    public void forceStop() {
        state = false;
    }

    public boolean getState() {
        return state;
    }
}