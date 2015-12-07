package ADD_ON;


import SIM.Position;
import SIM.Sensor;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
                    sensorManager.determineSensoring();
                    map.setPosNow(sim.getPosition().getX(), sim.getPosition().getY());
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
        map.setPosNow(pos.getX(), pos.getY());
        jscrollpane = new JScrollPane();
        jscrollpane.setBounds(12, 6, 320, 320);

        Position position = size;
        DefaultTableModel defaultTableModel = new DefaultTableModel(position.getX()+1, position.getY()+1);
        jtable = new JTable(defaultTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                JLabel label = new JLabel();

                if(tempMap[row][column]==1) {
                    label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/hazard.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                    return label;
                }
                else if(tempMap[row][column]==2) {
                    label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/colorblob.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                    return label;
                }
                else if(tempMap[row][column]==3) {
                    label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/spot.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                    return label;
                }
                else if(tempMap[row][column]>=5) {
                    switch(addonmain.getSim().getPosition().getDirection()){
                        case 1 :
                            label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/back.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                            break;
                        case 2 :
                            label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/right.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                            break;
                        case 3 :
                            label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/front.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                            break;
                        case 4 :
                            label.setIcon(new ImageIcon(new ImageIcon("/Users/WarYi/Desktop/left.png").getImage().getScaledInstance(340 / size.getY(), 340 / size.getY(), Image.SCALE_SMOOTH)));
                            break;
                        default :
                            break;
                    }
                    return label;
                }
                else {
                    c.setBackground(Color.white);
                    return c;
                }
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