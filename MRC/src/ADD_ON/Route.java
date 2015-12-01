package ADD_ON;

import SIM.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Route {
    private Queue<Position> route;
    private Queue<Integer> exe;

    public Route() {
        this.route = new LinkedList<Position>();
    }

    public Queue<Position> getRoute() {
        return route;
    }

    public void addRoute(Position route) {
        this.route.add(route);
    }

    public Position getNext(){
        return this.route.poll();
    }

    public void clearRoute(){
        route.clear();
    }

    public boolean isEmpty(){
        if(route.size() == 0)
            return true;
        else
            return false;
    }

    public void addExe(int exe) {
        this.exe.add(exe);
    }

    public int getExe(){
        return this.exe.poll();
    }
}
