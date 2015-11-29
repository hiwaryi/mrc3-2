package SIM;

import java.util.LinkedList;
import java.util.Queue;

public class Route {
    private Queue<Position> route;

    public Route() {
        this.route = new LinkedList<Position>();
    }

    public Queue<Position> getRoute() {
        return route;
    }

    public void setRoute(Queue<Position> route) {
        this.route = route;
    }

    public Position getNext(){
        return this.route.poll();
    }
}
