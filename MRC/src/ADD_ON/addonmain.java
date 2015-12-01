package ADD_ON;

import SIM.Position;
import SIM.Simmap;
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

        Simmap simmap = new Simmap(map.getMap(), map.getStart());
        sim sim = new sim(simmap);
        SensorManager sensorManager = new SensorManager(sim);
        Integer position = routeManager.orderNextStep();

        Scanner sc = new Scanner(System.in);
        System.out.println("start!!");

        String msg = "";
        while(position!=-1){
            System.out.println("now : "+sim.getPosition().getX()+", "+sim.getPosition().getY());
            switch (sim.getPosition().getDirection()){
                case 1:
                    msg = "front";
                    break;
                case 2:
                    msg = "right";
                    break;
                case 3:
                    msg = "back";
                    break;
                case 4:
                    msg = "left";
                    break;
                default:
                    break;
            }
            //System.out.println("now dircetion : "+msg);
            /*
            if(next==1 || next==0) {
                System.out.println("next : " + position.getX() + ", " + position.getY());
                position = routeManager.orderNextStep();
            }*/
            sim.setNextStep(position);
        }
        System.out.println("exploring finish");
    }
}
