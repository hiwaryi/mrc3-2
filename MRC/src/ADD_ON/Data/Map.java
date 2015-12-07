package ADD_ON.Data;

import SIM.Position;

import java.util.ArrayList;
import java.util.List;


public class Map {
    private int[][] mapData;
    private int w, h;
    private Position start;
    private List<Position> hazard, colorblob, predefinedSpot;

    public Map(int[][] map, Position start){
        this.mapData = map;
        this.start = start;
        hazard = new ArrayList<>();
        colorblob = new ArrayList<>();
        predefinedSpot = new ArrayList<>();
        h = this.mapData.length;
        w = this.mapData[0].length;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public Position getStart() {
        return start;
    }

    public List<Position> getHazard() {
        return this.hazard;
    }

    public void addHazard(Position hazard){
        int x = hazard.getX();
        int y = hazard.getY();

        mapData[y][x] = 1;
        this.hazard.add(hazard);
    }

    public void addColorblob(Position colorblob){
        int x = colorblob.getX();
        int y = colorblob.getY();

        mapData[y][x] = 2;
        this.colorblob.add(colorblob);
    }

    public List<Position> getPredefinedSpot() {
        return predefinedSpot;
    }

    public void addPredefinedSpot(Position predefinedSpot) {
        int x = predefinedSpot.getX();
        int y = predefinedSpot.getY();

        mapData[y][x] = 3;
        this.predefinedSpot.add(predefinedSpot);
    }

    public int getMapValueAt(Position Pos) {
        int x = Pos.getX();
        int y = Pos.getY();

        if(0 <= x && x < w && 0 <= y && y < h)
            return mapData[y][x];
        else
            return -1;
    }

    public int[][] getMapData() {
        return mapData;
    }

    public void setPosNow(int x, int y){
        mapData[y][x] += 5;
    }
    public void setPosEx(int x, int y){
        mapData[y][x] -= 5;
    }
}
