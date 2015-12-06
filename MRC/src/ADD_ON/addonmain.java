package ADD_ON;

import SIM.Simmap;
import SIM.sim;

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
        sensorManager = new SensorManager(sim, this);
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setMapManager(MapManager mapManager){
        this.mapManager = mapManager;
    }

    public Simmap getSimmap() {
        return simmap;
    }

    public RouteManager getRouteManager() {
        return routeManager;
    }

    public SensorManager getSensorManager() {
        return sensorManager;
    }

    public SIM.sim getSim() {
        return sim;
    }

    public static void main(String[] args) {
        addonmain addonmain = new addonmain();
    }
}