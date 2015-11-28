package SIM;

import java.util.Scanner;

/**
 * Created by kj on 2015-11-28.
 */
public class main {
/* 1 : hazard
   2 : color blob
*/
    public static void main(String[] args) {
        int[][] mapdata = {{0, 0, 1, 0, 1}, {2, 0, 1, 0, 0}, {1, 2, 1, 0, 0}, {0, 0, 2, 2, 1}, {0, 0, 1, 2, 0}};
        Position pos = new Position();
        Sensor sensor;
        map map = new map(mapdata, pos);
        sim sim = new sim();
        Scanner sc = new Scanner(System.in);
        while(sc.next().compareTo("-1")!=0){
            sensor = sim.sensoring();
            if(!sensor.isHazard()){

            }
        }
    }
}
