package ADD_ON.Control;
import ADD_ON.addonmain;
import SIM.*;


public class SensorManager {
    private boolean hazard;
    private Colorblob cb;
    private Position simPosition;
    private Sensor sensor;

    private RouteManager routeManager;
    private MapManager mapManager;

    private SIM.sim SIM;
    private addonmain addonmain;

    public SensorManager(sim SIM, addonmain addonmain) {
        this.SIM = SIM;
        hazard = false;
        this.addonmain = addonmain;
    }

    public void determineSensorData() {
        mapManager = addonmain.getMapManager();
        routeManager = addonmain.getRouteManager();
        simPosition = SIM.getPosition();

        Simmap simmap = addonmain.getSimmap();
        if(simPosition != simmap.getRealPos() ){   //김나라가 추가 현재 위치가  심의 현재 위치와 다르면 다시 루트 짜줌
            simPosition.setPosition(simmap.getRealPos());
            if(mapManager.getMap().getMapdata(simPosition) == 3)
                mapManager.getMap().getPredefinedSpot().remove(simPosition);
            routeManager.makeRoute(mapManager.getMap(), simPosition);
        }

        sensor = SIM.getSensorData();
        hazard = sensor.isHazard();
        cb = sensor.getCb();

        if(mapManager.getMap().getMapdata(simPosition) == 3)
            mapManager.getMap().getPredefinedSpot().remove(simPosition);

        if (hazard == true && mapManager.getMap().getMapdata(simPosition.front()) != 1) {
            Position front = simPosition.front();
            mapManager.updateHazard(front);
            routeManager.makeRoute(mapManager.getMap(), simPosition);
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