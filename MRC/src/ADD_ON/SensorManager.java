package ADD_ON;
import SIM.*;
;


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
        mapManager = addonmain.getMapManager();
        routeManager = addonmain.getRouteManager();
        pos = sim.getPosition();

        Simmap simmap = addonmain.getSimmap();
        if(pos != simmap.getRealPos() ){   //김나라가 추가 현재 위치가  심의 현재 위치와 다르면 다시 루트 짜줌
            pos.setPosition(simmap.getRealPos());
            if(mapManager.getMap().getMapdata(pos) == 3)
                mapManager.getMap().getPredefinedSpot().remove(pos);
            routeManager.makeRoute(mapManager.getMap(), pos);
        }

        sensor = sim.getSensorData();
        hazard = sensor.isHazard();
        cb = sensor.getCb();

        if(mapManager.getMap().getMapdata(pos) == 3)
            mapManager.getMap().getPredefinedSpot().remove(pos);

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