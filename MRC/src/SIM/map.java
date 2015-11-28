package SIM;

import java.util.Scanner;

/**
 * Created by kj on 2015-11-26.
 */

public class map {
    private int[][] map;
    private Position startPos, nowPos;

    //�ʱ⿡ ADD_ON���� ���� ������ �ִ� ���� , �ʱ� Ž����ġ�� �޾ƿ�
    public map(int[][] map,Position startPos){
        super();
        this.map = map;
        this.startPos =startPos;
    }

    //Sensor �κ��� ���� ��ġ ���� ��ȯ
    public int getMapdata(Position Pos) {
        int x = Pos.getX();
        int y = Pos.getY();
        return map[x][y];
    }

    //Hazard������ SIM�����κ��� �޾Ƽ� �Է�
    public boolean getHazard(Position nowPos) {
        Position check =nowPos.front();
        int x = check.getX();
        int y = check.getY();
        if(map[x][y]==1)
            return true;

        return false;
    }

    public void setHazard() {
        Scanner position = new Scanner(System.in); //���ο� hazard�� �Է�
        String p=position.nextLine();

        //����ڿ��� �Է¹޴� �������
      /*
      int x; int y;
      x=hazard.getX();
      y=hazard.getY();
      map[x][y]=1;

      */
        //���۷����Ϳ��Լ� �߰��� �Է¹޴� �κδ� �����ؾ���
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

    //���� SIM�� ��ġ ������ ǥ��
    public void setSIMPosition(Position nowPos){
        this.nowPos = nowPos;
    }
    //���� SIM�� ������ġ ��ȯ
    public Position getSIMPosition(){
        return  nowPos;
    }
}