package SIM;

import java.util.Random;

public class sim {
    private int nextStep, nowDirection;
    private final int FRONT = 1;
    private final int RIGHT = 2;
    private final int BACK = 3;
    private final int LEFT = 4;

    private Sensor sensor;
    private Position position;
    private Simmap map;
    private int w, h;

    public sim(Simmap map){
        this.map = map;
        position = map.getStart();
        nextStep = -1;
        w = map.getW();
        h = map.getH();
    }

    public void moveInterface() {
        Random random = new Random();
        // move
        if(nextStep!=-1) {
            nowDirection = position.getDirection();
            switch (nextStep) {
                case 1://move to front
                    int x = map.getRealPos().getX(), y = map.getRealPos().getY();
                    int r = random.nextInt(100);   // random number to raise SIM's malfunctioning
                    if(r<30 && r>=20 && 0 <= x - 2 && x + 2 <= w && 0 <= y - 2 && y + 2 <= h){
                        position.setPosition(position.front());
                        sensor = getSensorData();   // sensing before move twice to not get bombed
                        if(sensor.isHazard()==true){
                            System.out.print("SIM malfunctioned! (tried to move twice, but there is hazard spot)");
                            map.setRealPos(position);
                        }
                        else{
                            System.out.println("SIM malfunctioned! (moved twice)");
                            Position tmp = new Position(position.front().getX(), position.front().getY());
                            tmp.setDirection(position.getDirection());
                            map.setRealPos(tmp); // set real pos
                        }
                    }
                    else if(r < 20){
                        System.out.println("SIM malfunctioned! (didn't move)");
                        map.setRealPos(new Position(position.getX(), position.getY()));
                        position.setPosition(position.front());
                    }
                    else {
                        position.setPosition(position.front());
                        map.setRealPos(position);
                    }
                    break;
                case 2://turn right
                    nowDirection = (nowDirection + 1) % 4;
                    nowDirection = nowDirection == 0 ? 4 : nowDirection;
                    position.setDirection(nowDirection);
                    break;
                default:
                    break;
            }
        }
    }

    public void setNextStep(int next){
        //get next step from ADD-ON
        // 0: Exploring finished??
        // 1: move Front
        // 2: turn right;
        // 3: turn left;
        nextStep = next;
        moveInterface();
    }

    public Sensor getSensorData(){
        return sensoring();
    }


    public Sensor sensoring(){
        boolean hazard = false;
        Colorblob cb = new Colorblob();

        int frontData = map.getMapValueAt(position.front());
        int leftData = map.getMapValueAt(position.left());
        int rightData = map.getMapValueAt(position.right());
        int backData = map.getMapValueAt(position.back());

        // determine hazard
        if(frontData == 1)
            hazard = true;

        // determine colorblob
        if(frontData == 2)
            cb.setFront(true);
        if(leftData == 2)
            cb.setLeft(true);
        if(rightData == 2)
            cb.setRight(true);
        if(backData == 2)
            cb.setBack(true);

        Sensor mySensor = new Sensor(hazard, cb, position);

        return mySensor;
    }

    public Position getPosition() {
        return position;
    }
}