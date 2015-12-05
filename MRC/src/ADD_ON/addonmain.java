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
        mapForm = new MapForm(this);
    }

    public void yay(){
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

    public void setMapManager(MapManager mapManager){
        this.mapManager = mapManager;
    }

    public static void main(String[] args) {
        addonmain addonmain = new addonmain();
    }
}