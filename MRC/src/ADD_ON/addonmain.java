package ADD_ON;

import ADD_ON.Control.MapManager;
import ADD_ON.Control.RouteManager;
import ADD_ON.Control.SensorManager;
import ADD_ON.Data.Map;
import ADD_ON.Interface.MapForm;
import SIM.Position;
import SIM.Simmap;
import SIM.sim;

import javax.swing.JFrame;

public class addonmain extends JFrame{
    private MapManager mapManager;
    private MapForm mapForm;
    private Map map;
    private RouteManager routeManager;
    private Simmap simmap;
    private sim sim;
    private SensorManager sensorManager;

    public addonmain() {
        mapForm = new MapForm(this);
    }

    public void afterIntialize(){
        map = mapManager.getMap();
        routeManager = new RouteManager();
        routeManager.makeRoute(map, map.getStart());

        int y = map.getMapData().length, x = map.getMapData()[0].length;
        int[][] newMap = new int[y][x];
        for(int i = 0; i < y; i++){
            for(int j = 0; j < x; j++)
                newMap[i][j] = map.getMapValueAt(new Position(j, i));
        }

        simmap = new Simmap(newMap, map.getStart());
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
        new addonmain();
    }
}