package ADD_ON;

import SIM.Sensor;

import java.util.Scanner;

/**
 * Created by kj on 2015-11-29.
 */
public class MapForm {
    Scanner sc;
    String mapsize, startposition, predefinedspot, hazardspot, colorblob;
    MapManager mapManager;

    MapForm(){
        sc = new Scanner(System.in);
        EnterMapData();
        mapManager = new MapManager(mapsize, startposition, predefinedspot, hazardspot, colorblob);
    }

    private void EnterMapData(){
        System.out.println("init map");
        System.out.println("Map size : ");
        mapsize = sc.nextLine();

        System.out.println("Start Position : ");
        startposition = sc.nextLine();
        System.out.println("Predefined spot : ");
        predefinedspot = sc.nextLine();
        System.out.println("Hazard spot : ");
        hazardspot = sc.nextLine();
        System.out.println("Colorblob spot : ");
        colorblob = sc.nextLine();
    }
}
