package ADD_ON.Data;

import SIM.Position;

import java.util.LinkedList;
import java.util.Queue;

public class Route {
    private Queue<Position> route;
    private Queue<Integer> step;

    public Route() {
        this.route = new LinkedList<>();
        this.step = new LinkedList<>();
    }

    public void addRoute(Position route) {
        this.route.add(route);
    }

    public Position getNext(){
        return this.route.poll();
    }

    public boolean isEmpty(){
        if(route.size() == 0)
            return true;
        else
            return false;
    }

    public void addStep(int exe) {
        this.step.add(exe);
    }

    public Integer getStep(){
        Integer returnVal = this.step.poll();
        if(returnVal != null)
            return returnVal;
        else
            return -1;
    }
}
