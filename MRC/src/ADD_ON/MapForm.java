package ADD_ON;


import SIM.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * Created by kj on 2015-11-29.
 */
public class MapForm extends JFrame{
    private MapManager mapManager;
    private JTextArea mapData;
    private JTextArea hiddenHazard;
    private JTextArea textArea;
    private JButton EnterData;
    private JButton AddData;
    private JButton next;
    private JLabel hiddenLabel;
    private JLabel initLabel;
    private addonmain addonmain;

    public MapForm(addonmain addonmain) {
        getContentPane().setLayout(null);
        setSize(700, 700);

        textArea = new JTextArea();
        textArea.setBounds(12, 6, 325, 321);
        textArea.setLineWrap(true);
        getContentPane().add(textArea);

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

        EnterData = new JButton("Enter Data");
        EnterData.setBounds(349, 304, 97, 23);
        getContentPane().add(EnterData);

        AddData = new JButton("Add Data");
        AddData.setBounds(456, 304, 97, 23);
        getContentPane().add(AddData);

        next = new JButton("next");
        next.setBounds(563, 304, 97, 23);
        getContentPane().add(next);

        setVisible(true);

        this.addonmain = addonmain;

        EnterData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = mapData.getText().toString();
                mapManager = new MapManager(data);

                addonmain.setMapManager(mapManager);
                addonmain.yay();
            }
        });

        AddData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spot = hiddenHazard.getText().toString();
                StringTokenizer token = new StringTokenizer(spot, ")");
                int x = 0, y = 0;

                spot = spot.substring(1, spot.length()-1);
                token = new StringTokenizer(spot, ")");
                while(token.hasMoreTokens()){
                    String temp = token.nextToken();
                    temp = temp + ")";
                    int index = temp.indexOf(",");
                    x = Integer.parseInt(temp.substring(1, index));
                    y = Integer.parseInt(temp.substring(index+1, temp.length()-1));
                    addonmain.getSimmap().addHazard(new Position(x, y));
                }
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SIM.sim sim = addonmain.getSim();
                RouteManager routeManager = addonmain.getRouteManager();
                int nextStep = routeManager.orderNextStep();

                if(nextStep != -1) {
                    sim.setNextStep(nextStep);
                    System.out.println("now : " + sim.getPosition().getX() + ", " + sim.getPosition().getY());
                }
                else
                    System.out.println("finish!");
            }
        });
    }

    public MapManager getMapManager(){
        return this.mapManager;
    }
}