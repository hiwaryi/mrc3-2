package ADD_ON;

import SIM.*;

import java.util.ArrayList;
import java.util.List;

class Astar { // blocks for astar
    private Position parent;
    private Position cur;
    private int F, G, H;
    private int state; // 0 : nothing, 1 : hazard, 2 : openList, 3 : closedList

    public Astar(Position cur){
        parent = null;
        F = 0;
        G = 0;
        H = 0;
        state = 0;
        this.cur = cur;
    }

    public Position getParent() {
        return parent;
    }

    public void setParent(Position parent) {
        this.parent = parent;
    }

    public Position getCur() {
        return cur;
    }

    public void setCur(Position cur) {
        this.cur = cur;
    }

    public int getF() {
        return F;
    }

    public void calcF() {
        F = G + H;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

public class RouteManager {
    private Position expectedPosition;
    private Route route;
    private map map;

    public RouteManager() {
        this.expectedPosition = new Position();
        this.route = new Route();
    }

    public Position getExpectedPosition() {
        return expectedPosition;
    }

    public void setExpectedPosition(Position expectedPosition) {
        this.expectedPosition = expectedPosition;
    }

    // composite a* algorithm
    private void a_star(Position from, Position to){
        Position cur = from;
        int w = map.getW(), h = map.getH();
        Astar[][] astar  = new Astar[h][w];
        List<Position> openList = new ArrayList<Position>();
        List<Position> closedList = new ArrayList<Position>();
        int[][] mapArray = map.getMap();
        int[][] d = {
                {0, -1}, {-1, 0}, {1, 0}, {0, 1}
        };

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++)
                astar[i][j] = new Astar(new Position(j, i));
        }

        // initializing
        for(Position pos : map.getHazard()){
            if(map.getMapdata(pos) == 1)
                astar[pos.getY()][pos.getX()].setState(1);
        }

        astar[cur.getY()][cur.getX()].setH(Math.abs(cur.getX() - to.getX()) + Math.abs(cur.getY() - to.getY()));
        openList.add(cur);

        // start finding path
        while(!openList.isEmpty()){
            openList.remove(cur);
            closedList.add(cur);
            astar[cur.getY()][cur.getX()].setState(3);
            int x = cur.getX(), y = cur.getY();
            int pivot = openList.size();

            // if reached destination, break the while
            if(mapArray[y][x] == 3 && (x != from.getX() || y != from.getY()))
                break;

            // add surrounding blocks into openList
            for(int i = 0; i < 4; i++){
                Position newPos = new Position(x + d[i][0], y + d[i][1]);
                int newX = newPos.getX(); // the spot i'm looking at(surrounding spot)
                int newY = newPos.getY();
                if(0 <= newX && newX < w && 0 <= newY && newY < h){
                    if((mapArray[newY][newX] != 1) && astar[newY][newX].getState() == 0 ){ // if newPos is available
                        openList.add(newPos);
                        astar[newY][newX].setState(2); // set this block as openList
                        astar[newY][newX].setParent(new Position(x, y));
                    }
                }
            }

            // calculate F
            for(int i = pivot; i < openList.size(); i++){
                x = openList.get(i).getX();
                y = openList.get(i).getY();

                // set G
                int distance = Math.abs(x - from.getX()) + Math.abs(y - from.getY());
                astar[y][x].setG(distance);
                // set H
                distance = Math.abs(x - to.getX()) + Math.abs(y - to.getY());
                astar[y][x].setH(distance);
                // set F
                astar[y][x].calcF();
            }

            // search min F
            int min  = Integer.MAX_VALUE;
            Position minPos = from;
            for(int i = 0; i < openList.size(); i++){
                x = openList.get(i).getX();
                y = openList.get(i).getY();
                int F = astar[y][x].getF();

                if(min > F){
                    min = F;
                    minPos = openList.get(i);
                }
            }

            if(minPos != cur)
                cur = minPos;
        }

        // save Path
        List<Position> path = new ArrayList<Position>();
        Position lastNode = to;
        while(lastNode != from && lastNode != null){
            Astar newAstar = astar[lastNode.getY()][lastNode.getX()];

            path.add(newAstar.getCur());
            lastNode = newAstar.getParent();
        }

        // reverse the path
        for(int i = path.size() - 2; i >= 0; i--) {
            route.addRoute(path.get(i));
            System.out.println(path.get(i).getX() + ", " + path.get(i).getY());
        }
    }

    public void makeRoute(map map){
        Position cur = new Position();
        int w = map.getW(), h = map.getH();
        List<Position> predefinedSpot = map.getPredefinedSpot();
        boolean[] check = new boolean[map.getPredefinedSpot().size()];
        for(int i = 0; i < map.getPredefinedSpot().size(); i++)
            check[i] = false;
        this.map = map;

        int repeat = predefinedSpot.size();
        while(repeat-- > 0){
            int min = Integer.MAX_VALUE; // min distance from current position
            int next_index = -1; // index of min distance spot

            // searching for min distance spot from current position
            for(int i = 0; i < predefinedSpot.size(); i++){
                Position tmp = predefinedSpot.get(i);
                int distance = Math.abs(tmp.getX() - cur.getX()) + Math.abs(tmp.getY() - cur.getY());

                if(min > distance){
                    min = distance;
                    next_index = i;
                }
            }

            a_star(cur, predefinedSpot.get(next_index)); // get route
            cur = predefinedSpot.remove(next_index); // make min distance spot to current position
        }

        Position now = map.getStart();
        while(!route.isEmpty()){
            Position next = route.getNext();

            if(now.front().equals(next))
                route.addExe(1);
            else if(now.left().equals(next))
                route.addExe(3);
            else if(now.right().equals(next))
                route.addExe(2);
            else if(now.back().equals(next))
                route.addExe(4);
        }
    }

    public int orderNextStep(){
        return route.getExe();
    }
}
