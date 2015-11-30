package SIM;

import java.util.Scanner;

/**
 * Created by kj on 2015-11-28.
 */
public class simmain {
/* 1 : hazard
   2 : color blob

    public static void main(String[] args) {
        int[][] mapdata = {{0, 0, 1, 0, 1}, {2, 0, 1, 0, 0}, {1, 2, 1, 0, 0}, {0, 0, 2, 2, 1}, {0, 0, 1, 2, 0}};
        Position pos = new Position();
        Sensor sensor;
        map map = new map(mapdata, pos);
        sim sim = new sim(map);
        System.out.println("start");
        Scanner sc = new Scanner(System.in);
        String msg = sc.next();
        while(msg.compareTo("-1")!=0){
            sensor = sim.sensoring();
            if(!sensor.isHazard()){
                sim.setNextStep(Integer.parseInt(msg));
            }else{
                System.out.println("is hazard!!");
                sim.setNextStep(Integer.parseInt(msg));
            }

            if(sensor.getCb().getFront()){
                System.out.println("is ColorBlob!!");
            }

            System.out.println("now position : "+  sim.getPosition().getX()+", "+sim.getPosition().getY());
            msg=sc.next();
        }
    }
    */
}
