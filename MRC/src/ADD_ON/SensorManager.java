package ADD_ON;
import SIM.Position;
import SIM.Sensor;
import SIM.Colorblob;
import SIM.sim;;


public class SensorManager {

    private boolean hazard;
    private Colorblob cb;
    private Position pos;
    private Sensor sensor;
    private RouteManager routeManager;
    private SIM.sim sim;
    private MapManager mapManager;
    private addonmain addonmain;

    public SensorManager(sim sim, addonmain addonmain) {
        this.sim = sim;
        hazard = false;
        this.addonmain = addonmain;
    }

    public void determineSensoring() {
        sensor = sim.getSensorData();
        pos = sim.getPosition();

        hazard = sensor.isHazard();
        cb = sensor.getCb();
        mapManager = addonmain.getMapManager();
        routeManager = addonmain.getRouteManager();

        if (hazard == true && mapManager.getMap().getMapdata(pos.front()) != 1) {
            Position front = pos.front();
            mapManager.updateHazard(front);
            routeManager.makeRoute(mapManager.getMap(), pos);
            System.out.println("Found Hazard!!");
        }

        if (cb.getFront() == true)
            mapManager.updateColorblob(sensor.getPos().front());
        if (cb.getLeft() == true)
            mapManager.updateColorblob(sensor.getPos().left());
        if (cb.getRight() == true)
            mapManager.updateColorblob(sensor.getPos().right());
        if (cb.getBack() == true)
            mapManager.updateColorblob(sensor.getPos().back());
    }
}