package ADD_ON;

import SIM.Position;

import java.util.ArrayList;
import java.util.List;


public class map {
    private int[][] map;
    private int w, h;
    private Position start;
    private List<Position> hazard, colorblob, predefinedSpot;

    public map(int[][] map, Position start){
        this.map = map;
        this.start = start;
        hazard = new ArrayList<Position>();
        colorblob = new ArrayList<Position>();
        predefinedSpot = new ArrayList<Position>();
        h = this.map.length;
        w = this.map[0].length;
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

        map[y][x] = 1;
        this.hazard.add(hazard);
    }

    public List<Position> getColorblob() {
        return colorblob;
    }

    public void addColorblob(Position colorblob){
        int x = colorblob.getX();
        int y = colorblob.getY();

        map[y][x] = 2;
        this.colorblob.add(colorblob);
    }

    public List<Position> getPredefinedSpot() {
        return predefinedSpot;
    }

    public void addPredefinedSpot(Position predefinedSpot) {
        int x = predefinedSpot.getX();
        int y = predefinedSpot.getY();

        map[y][x] = 3;
        this.predefinedSpot.add(predefinedSpot);
    }

    public int getMapdata(Position Pos) {
        int x = Pos.getX();
        int y = Pos.getY();

        if(0 <= x && x < w && 0 <= y && y < h)
            return map[y][x];
        else
            return -1;
    }

    public int[][] getMap() {
        return map;
    }

    public void setPosNow(int x, int y){
        map[y][x] += 5;
    }
    public void setPosEx(int x, int y){
        map[y][x] -= 5;
    }
}