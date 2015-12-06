package SIM;

import java.util.Random;

public class sim {
    private boolean state;
    private int nextStep, nowDirection;
    private final int FRONT = 1;
    private final int RIGHT = 2;
    private final int BACK = 3;
    private final int LEFT = 4;

    private Sensor sensor;
    private Position position;
    private Simmap map;

    public sim(Simmap map){
        this.map = map;
        position = map.getStart();
        state = false;
        nextStep = -1;
    }

    public void moveInterface() {
        Random random = new Random();
        //  int xPos, yPos;
        // move
        if(nextStep!=-1) {
            state = true;
            nowDirection = position.getDirection();
            switch (nextStep) {
                case 1://move to front
                    /*
                    int r = random.nextInt(100);   //�??��?���? ?��?��     ?��?��?�� �?�? ?��?��?���? ?��?��?�� ?��?��?��
                    if(r<30 && r>=20){
                        position.setPosition(position.front());
                        sensor = getSensorData();   //?��?�� ??직이기전 ?��?��?��
                        if(sensor.isHazard()==true){
                            System.out.print("Sim?�� 2칸을 �??��?���?�? ?��?�� hazard�? 발견?��?�� 1칸만 �?");
                            map.setRealPos(position);
                        }
                        else{
                            System.out.println("Sim?�� ?��?��?��?�� ?�� 2칸을 �?");
                            Position tmp = new Position(position.front().getX(), position.front().getY());
                            tmp.setDirection(position.getDirection());
                            map.setRealPos(tmp); // ?��?��?�� ?��칸�?�? ?��기서 ?���? 갔으?�� ?���? �? 값이 ?��?��
                        }
                    }
                    else if(r < 20){
                        System.out.println("Sim?�� ?��?��?��?�� ?�� ??직이�? ?��?��");
                        map.setRealPos(new Position(position.getX(), position.getY()));
                        position.setPosition(position.front());

                    }

                    else {
                        position.setPosition(position.front());
                        map.setRealPos(position);
                    }*/
                    position.setPosition(position.front());
                    map.setRealPos(position);
                    break;
                case 2://turn right
                    nowDirection = (nowDirection + 1) % 4;
                    nowDirection = nowDirection == 0 ? 4 : nowDirection;
                    position.setDirection(nowDirection);
                    break;
                case 3://turn left
                    nowDirection = (nowDirection - 1) % 4;
                    nowDirection = nowDirection == 0 ? 4 : nowDirection;
                    position.setDirection(nowDirection);
                    break;
                default:
                    break;
            }
            state = false;
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

        int frontData = map.getMapdata(position.front());
        int leftData = map.getMapdata(position.left());
        int rightData = map.getMapdata(position.right());
        int backData = map.getMapdata(position.back());

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

        // get Current Position
//        Position pos = map.getRealPos();

        Sensor mySensor = new Sensor(hazard, cb, position);

        return mySensor;
    }

    public void forceStop() {
        state = false;
    }

    public boolean getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }
}