package ADD_ON;

import SIM.Position;
import SIM.Simmap;
import SIM.sim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;

public class addonmain extends JFrame{
    private MapManager mapManager;
    private MapForm mapForm;
    private map map;
    private RouteManager routeManager;
    private Simmap simmap;
    private sim sim;
    private SensorManager sensorManager;

    public addonmain() {

        mapForm = new MapForm();
        mapManager = mapForm.EnterMapData();
        while(mapManager==null) {
            mapManager = mapForm.getMapManager();
            if(mapManager == null)
                System.out.println("is null"); // <- 여기 고치면 안돌아감. 미스테리
        }
        //mapManager.printMap();
        map = mapManager.getMap();
        routeManager = new RouteManager();
        routeManager.makeRoute(map);

        simmap = new Simmap(map.getMap(), map.getStart());
        sim = new sim(simmap);
        sensorManager = new SensorManager(sim);

        Integer position = routeManager.orderNextStep();
        while(position!=-1){
            sim.setNextStep(position);
            System.out.println("now : "+sim.getPosition().getX()+", "+sim.getPosition().getY());
            position = routeManager.orderNextStep();
        }
        System.out.println("finish");
    }

    public static void main(String[] args) {

        addonmain addonmain = new addonmain();

    }
}
