package ADD_ON;

import SIM.Position;

import java.util.List;


public class map {
    private int[][] map;
    private Position start, nowPos;
    private List<Position> hazard, colorblob, predefinedSpot;

    public map(int[][] map, Position start){
        this.map = map;
        this.start = start;
        this.nowPos = start;
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

        return map[x][y];
    }

    public int[][] getMap() {
        return map;
    }

    public Position getSIMPosition(){
        return  nowPos;
    }
}