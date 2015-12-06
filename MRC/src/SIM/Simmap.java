package SIM;

import ADD_ON.map;

/**
 * Created by kj on 2015-12-01.
 */
public class Simmap extends map {
    private Position realPos;

    public Simmap(int[][] map, Position start) {
        super(map, start);
    }

    public Position getRealPos() {
        return realPos;
    }

    public void setRealPos(Position realPos) {
        this.realPos = realPos;
    }
}
