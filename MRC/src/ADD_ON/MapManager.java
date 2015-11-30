package ADD_ON;

import SIM.Position;
import SIM.map;

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

    private ADD_ON.map map;
    private Position startPosition;
    public int[][] tempMap;
    public int mapX, mapY;
    private StringTokenizer token;

    MapManager(String size, String start, String preSpot, String hazardSpot, String colorblob){
        initMap(size, start, preSpot, hazardSpot, colorblob);
    }

    public void initMap(String size, String start, String preSpot, String hazardSpot, String colorblob){
        makeMap(size);
        map = new ADD_ON.map(tempMap, getStartPosition(start));

        parseMapData(HAZARD, hazardSpot);
        parseMapData(COLORBLOB, colorblob);
        parseMapData(PREDEFINED, preSpot);
        //map = new ADD_ON.map(tempMap, getStartPosition(start));
        //printMap();
    }

    private void parseMapData(int what, String spot){
        switch (what){
            case HAZARD:
                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(" ");
                    int x = Integer.parseInt(temp.substring(1, index));
                    int y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    map.addHazard(new Position(x, y));
                    // tempMap[y][x] = HAZARD;
                }
                break;
            case COLORBLOB:
                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(" ");
                    int x = Integer.parseInt(temp.substring(1, index));
                    int y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    map.addColorblob(new Position(x, y));
//                    tempMap[y][x] = COLORBLOB;
                }
                break;
            case PREDEFINED:
                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(" ");
                    int x = Integer.parseInt(temp.substring(1, index));
                    int y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    map.addPredefinedSpot(new Position(x, y));
//                    tempMap[y][x] = PREDEFINED;
                }
                break;
            default:
                break;
        }
    }

    public void updateColorblob(Position colorblob){
        map.addColorblob(colorblob);
    }

    public void updateHazard(Position hazard){
        map.addHazard(hazard);
    }

    private void makeMap(String size){
        int index = size.indexOf(" ");
        mapX = Integer.parseInt(size.substring(1, index));
        mapY = Integer.parseInt(size.substring(index+1, size.length()-1));
        System.out.println("map size is : "+mapX+", "+mapY);
        tempMap = new int[mapY+1][mapX+1];
        for(int i=0; i<=mapY; i++){
            for(int j=0; j<=mapX; j++) {
                tempMap[i][j] = EMPTY;
            }
        }
    }

    private Position getStartPosition(String start){
        int index = start.indexOf(" ");
        int x = Integer.parseInt(start.substring(1, index));
        int y = Integer.parseInt(start.substring(index+1, start.length()-1));
        System.out.println("start position is : "+x+", "+y);
        startPosition = new Position(x, y);
        return startPosition;
    }

    public void printMap(){
        System.out.println("print map("+this.mapX+","+this.mapY+")");

        for(int i=mapY; i>=0; i--){
            for(int j=mapX; j>=0; j--){
                System.out.print(tempMap[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    public ADD_ON.map getMap(){
        return map;
    }
}
