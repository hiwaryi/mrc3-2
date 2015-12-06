package ADD_ON;

import SIM.SimMap;
import SIM.sim;

import javax.swing.JFrame;

public class addonmain extends JFrame{
    private MapManager mapManager;
    private MapForm mapForm;
    private map map;
    private RouteManager routeManager;
    private SimMap simMap;
    private sim sim;
    private SensorManager sensorManager;

    public addonmain() {
        mapForm = new MapForm(this);
    }

    public void yay(){
        map = mapManager.getMap();
        routeManager = new RouteManager();
        routeManager.makeRoute(map);

        simMap = new SimMap(map.getMap(), map.getStart());
        sim = new sim(simMap);
        sensorManager = new SensorManager(sim);

        Integer position = routeManager.orderNextStep();
        System.out.println("finish");
    }

    public void setMapManager(MapManager mapManager){
        this.mapManager = mapManager;
    }

    public SimMap getSimMap() {
        return simMap;
    }

    public static void main(String[] args) {
        addonmain addonmain = new addonmain();
    }
}