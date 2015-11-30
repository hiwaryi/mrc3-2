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

    public SensorManager(){
        sim = new sim();
        hazard = false;
        determineSensoring();
    }

    public void determineSensoring(){
        sensor = sim.getSensorData();
        pos = sensor.getPos();
        hazard = sensor.isHazard();
        cb = sensor.getCb();

        if(hazard == true){
            mapManager.updateHazard(sensor.getPos().front());
            routeManager.makeRoute();
        }

        if(cb.getFront()==true)
            mapManager.updateColorblob(sensor.getPos().front());
        if(cb.getLeft()==true)
            mapManager.updateColorblob(sensor.getPos().left());
        if(cb.getRight()==true)
            mapManager.updateColorblob(sensor.getPos().right());
        if(cb.getBack()==true)
            mapManager.updateColorblob(sensor.getPos().back());
    }
}
		   
		   
	
	
