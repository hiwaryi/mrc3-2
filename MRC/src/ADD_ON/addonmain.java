package ADD_ON;

/**
 * Created by kj on 2015-11-29.
 */
public class addonmain {

    public static void main(String[] args) {
        MapForm mapForm = new MapForm();
        MapManager mapManager = mapForm.EnterMapData();
        map map = mapManager.getMap();
        RouteManager routeManager = new RouteManager();
        routeManager.makeRoute();
    }
}
