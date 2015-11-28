package SIM;

import java.util.Scanner;

/**
 * Created by kj on 2015-11-26.
 */

public class map {
    private int[][] map;
    private Position startPos, nowPos;

    //초기에 ADD_ON으로 부터 지도에 있는 정보 , 초기 탐색위치를 받아옴
    public map(int[][] map,Position startPos){
        super();
        this.map = map;
        this.startPos =startPos;
    }

    //Sensor 로부터 받은 위치 정보 반환
    public int getMapdata(Position Pos) {
        int x = Pos.getX();
        int y = Pos.getY();
        return map[x][y];
    }

    //Hazard정보를 SIM센서로부터 받아서 입력
    public boolean getHazard(Position nowPos) {
        Position check =nowPos.front();
        int x = check.getX();
        int y = check.getY();
        if(map[x][y]==1)
            return true;

        return false;
    }

    public void setHazard() {
        Scanner position = new Scanner(System.in); //새로운 hazard값 입력
        String p=position.nextLine();

        //사용자에게 입력받는 방식으로
      /*
      int x; int y;
      x=hazard.getX();
      y=hazard.getY();
      map[x][y]=1;

      */
        //오퍼레이터에게서 중간에 입력받는 부부늘 구현해야함
    }

    public boolean getColorblobs() {
        return true;
    }

    public void setColorblobs() {
        Scanner position = new Scanner(System.in);
        String p = position.nextLine();

      /*
      int x; int y;
      x=colorblobs.getX();
      y=colorblobs.getY();
      map[x][y]=2;
      */
    }

    public Position getStartPosition(){
        return startPos;
    }

    //현재 SIM의 위치 지도에 표시
    public void setSIMPosition(Position nowPos){
        this.nowPos = nowPos;
    }
    //현재 SIM의 지도위치 반환
    public Position getSIMPosition(){
        return  nowPos;
    }
}