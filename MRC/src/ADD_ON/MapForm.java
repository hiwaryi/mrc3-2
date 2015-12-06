package ADD_ON;


import SIM.Position;
import SIM.Sensor;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by kj on 2015-11-29.
 */
public class MapForm extends JFrame{
    private MapManager mapManager;
    private JTextArea mapData;
    private JTextArea hiddenHazard;
    private JTextArea hiddenColorblob;
    private JButton EnterData;
    private JButton hiddenHazardButton;
    private JButton hiddenColorblobButton;
    private JButton next;
    private JLabel hiddenLabel;
    private JLabel initLabel;
    private addonmain addonmain;
    private JScrollPane jscrollpane;
    private JTable jtable;
    private TableColumn column;
    private map map;
    private Position position;

    public MapForm(addonmain addonmain) {
        getContentPane().setLayout(null);
        setSize(700, 700);

        initLabel = new JLabel("Enter Init Map data");
        initLabel.setBounds(349, 6, 184, 15);
        getContentPane().add(initLabel);

        hiddenLabel = new JLabel("Enter hidden hazard spot");
        hiddenLabel.setBounds(349, 179, 184, 15);
        getContentPane().add(hiddenLabel);

        mapData = new JTextArea();
        mapData.setBounds(349, 31, 184, 114);
        getContentPane().add(mapData);
        mapData.setColumns(10);

        hiddenHazard = new JTextArea();
        hiddenHazard.setBounds(349, 204, 184, 51);
        getContentPane().add(hiddenHazard);
        hiddenHazard.setColumns(10);

        hiddenColorblob = new JTextArea();
        hiddenColorblob.setBounds(559, 204, 184, 51);
        getContentPane().add(hiddenColorblob);
        hiddenColorblob.setColumns(10);

        EnterData = new JButton("Enter Data");
        EnterData.setBounds(349, 304, 97, 23);
        getContentPane().add(EnterData);

        hiddenHazardButton = new JButton("Hidden Hazard");
        hiddenHazardButton.setBounds(456, 304, 97, 23);
        getContentPane().add(hiddenHazardButton);

        hiddenColorblobButton = new JButton("Hidden Colorblob");
        hiddenColorblobButton.setBounds(456, 334, 97, 23);
        getContentPane().add(hiddenColorblobButton);

        next = new JButton("next");
        next.setBounds(563, 304, 97, 23);
        getContentPane().add(next);

        getContentPane().setLayout(null);
        setSize(800, 500);

        initLabel = new JLabel("Enter Init Map data");
        initLabel.setBounds(349, 6, 184, 15);
        getContentPane().add(initLabel);

        hiddenLabel = new JLabel("Enter hidden hazard spot");
        hiddenLabel.setBounds(349, 179, 184, 15);
        getContentPane().add(hiddenLabel);

        setVisible(true);

        this.addonmain = addonmain;

        EnterData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = mapData.getText().toString();
                mapManager = new MapManager(data);

                addonmain.setMapManager(mapManager);
                map = mapManager.getMap();
                MakeMapForm(mapManager.getMapSize());
                addonmain.yay();
            }
        });

        hiddenHazardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spot = hiddenHazard.getText().toString();
                StringTokenizer token;
                int x, y;

                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(",");
                    x = Integer.parseInt(temp.substring(1, index));
                    y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    SIM.Simmap simMap = addonmain.getSimmap();
                    simMap.addHazard(new Position(x, y));
                }
            }
        });

        hiddenColorblobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spot = hiddenColorblob.getText().toString();
                StringTokenizer token;
                int x, y;

                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(",");
                    x = Integer.parseInt(temp.substring(1, index));
                    y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    SIM.Simmap simMap = addonmain.getSimmap();
                    simMap.addColorblob(new Position(x, y));
                }
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SIM.sim sim = addonmain.getSim();
                SensorManager sensorManager = addonmain.getSensorManager();
                RouteManager routeManager = addonmain.getRouteManager();
                int nextStep = routeManager.orderNextStep();

                if(nextStep != -1) {
                    map.setPosEx(sim.getPosition().getX(), sim.getPosition().getY());
                    sim.setNextStep(nextStep);
                    System.out.println("now : " + sim.getPosition().getX() + ", " + sim.getPosition().getY());
                    map.setPosNow(sim.getPosition().getX(), sim.getPosition().getY());
                    sensorManager.determineSensoring();
                }
                else
                    System.out.println("finish!");

                jtable.updateUI();
            }
        });
    }

    public void MakeMapForm(Position size){
        int interval;
        int[][] tempMap = map.getMap();
        Position pos = map.getStart();
        jscrollpane = new JScrollPane();
        jscrollpane.setBounds(12, 6, 320, 320);

        Position position = size;
        DefaultTableModel defaultTableModel = new DefaultTableModel(position.getX()+1, position.getY()+1);
        jtable = new JTable(defaultTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if(tempMap[row][column]==1)
                    c.setBackground(Color.RED);
                else if(tempMap[row][column]==2)
                    c.setBackground(Color.GREEN);
                else if(tempMap[row][column]==3)
                    c.setBackground(Color.BLUE);
                else if(tempMap[row][column]==5)
                    c.setBackground(Color.CYAN);
                else
                    c.setBackground(Color.white);

                return c;
            }
        };
        jtable.setFillsViewportHeight(true);
        jtable.setColumnSelectionAllowed(true);
        jtable.setCellSelectionEnabled(true);
        jtable.setTableHeader(null);
        jtable.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);

        if(size.getY()<19){
            jtable.setRowHeight(320/size.getY());
            interval = 320/size.getY();
        }else{
            interval = jtable.getRowHeight();
        }

        for (int i = 0; i < size.getX(); i++) {
            column = jtable.getColumnModel().getColumn(i);
            column.setPreferredWidth(interval);
        }

        jscrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jscrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jscrollpane.add(jtable);
        jscrollpane.setViewportView(jtable);
        jscrollpane.updateUI();
        getContentPane().add(jscrollpane);
    }

}