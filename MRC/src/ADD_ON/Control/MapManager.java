package ADD_ON.Control;

import ADD_ON.Data.Map;
import ADD_ON.Interface.MapForm;
import SIM.Position;
import SIM.Simmap;

import java.util.StringTokenizer;

/**
 * Created by kj on 2015-11-29.
 */
public class MapManager {

    public static final int EMPTY = 0;
    public static final int HAZARD = 1;
    public static final int COLORBLOB = 2;
    public static final int PREDEFINED = 3;
    public static final int HIDEHAZARD = 4;

    private Map Map;
    private Simmap simmap;
    private Position startPosition;
    public int[][] tempMap;
    public int mapX, mapY;
    private StringTokenizer token;
    private Position mapSize;
    private MapForm mapForm;

    public MapManager(String mapData){
        System.out.println(mapData);
        initMap(mapData);
    }

    public void initMap(String mapData){
        String temp[] = mapData.split("\\r?\\n");
        for(int i=0; i<temp.length; i++){
            switch(i){
                case 0:
                    makeMap(temp[i]);
                    break;
                case 1:
                    Map = new Map(tempMap, getStartPosition(temp[i]));
                    break;
                case 2:
                    parseMapData(HAZARD, temp[i]);
                    break;
//                case 3:
//                    parseMapData(COLORBLOB, temp[i]);
//                    break;
                case 3:
                    parseMapData(PREDEFINED, temp[i]);
                    break;
                default:
                    break;
            }
        }
    }

    private void makeMap(String size){
        int index = size.indexOf(",");
        mapX = Integer.parseInt(size.substring(1, index));
        mapY = Integer.parseInt(size.substring(index+1, size.length()-1));
        mapSize = new Position(mapX, mapY);

        tempMap = new int[mapY+1][mapX+1];
        for(int i=0; i<=mapY; i++){
            for(int j=0; j<=mapX; j++) {
                tempMap[i][j] = EMPTY;
            }
        }
    }

    private void parseMapData(int what, String spot){
        switch (what){
            case HAZARD:
                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(",");
                    int x = Integer.parseInt(temp.substring(1, index));
                    int y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    Map.addHazard(new Position(x, y));
                }
                break;
//            case COLORBLOB:
//                spot = spot.substring(1, spot.length()-1);
//                token = new StringTokenizer(spot, ")");
//                while(token.hasMoreTokens()){
//                    String temp = token.nextToken();
//                    temp = temp + ")";
//                    int index = temp.indexOf(",");
//                    int x = Integer.parseInt(temp.substring(1, index));
//                    int y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
//                    Map.addColorblob(new Position(x, y));
//                }
//                break;
            case PREDEFINED:
                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(",");
                    int x = Integer.parseInt(temp.substring(1, index));
                    int y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    Map.addPredefinedSpot(new Position(x, y));
                }
                break;
            default:
                break;
        }
    }

    public void updateColorblob(Position colorblob){
        int x = colorblob.getX(), y = colorblob.getY();
        if(0 <= x && x <= mapX && 0 <= y && y <= mapY)
            Map.addColorblob(colorblob);
    }

    public void updateHazard(Position hazard){
        int x = hazard.getX(), y = hazard.getY();
        if(0 <= x && x <= mapX && 0 <= y && y <= mapY)
            Map.addHazard(hazard);
    }

    private Position getStartPosition(String start){
        int index = start.indexOf(",");
        int x = Integer.parseInt(start.substring(1, index));
        int y = Integer.parseInt(start.substring(index+1, start.length()-1));
        startPosition = new Position(x, y);
        return startPosition;
    }

    public Map getMap(){
        return Map;
    }

    public Position getMapSize(){
        return mapSize;
    }
}
