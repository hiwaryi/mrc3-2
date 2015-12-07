package SIM;

import ADD_ON.Data.Map;

/**
 * Created by kj on 2015-12-01.
 */
public class Simmap extends Map {
    private Position realPos;

    public Simmap(int[][] map, Position start) {
        super(map, start);
        realPos = start;
    }

    public Position getRealPos() {
        return realPos;
    }

    public void setRealPos(Position realPos) {
        this.realPos = realPos;
    }
}
