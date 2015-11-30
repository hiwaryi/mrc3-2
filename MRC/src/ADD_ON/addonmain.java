package ADD_ON;

import SIM.Position;
import SIM.sim;

import java.util.Scanner;

/**
 * Created by kj on 2015-11-29.
 */
public class addonmain {

    public static void main(String[] args) {
        MapForm mapForm = new MapForm();
        MapManager mapManager = mapForm.EnterMapData();
        map map = mapManager.getMap();

        RouteManager routeManager = new RouteManager();
        routeManager.makeRoute(map);

        sim sim = new sim();
        SensorManager sensorManager = new SensorManager(sim);
        Position position = routeManager.orderNextStep();

        Scanner sc = new Scanner(System.in);
        System.out.println("start!!");
        while(position!=null){
            System.out.println("next : "+position.getX()+", "+position.getY());
            //sim.sensoring();
            //sim.setNextStep();
            int next = sc.nextInt();
            sim.setNextStep(next);
            position = routeManager.orderNextStep();
        }
    }
}
