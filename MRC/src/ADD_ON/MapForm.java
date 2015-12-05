package ADD_ON;

import SIM.Sensor;
import SIM.Simmap;
import SIM.sim;
import SIM.Position;
import SIM.Simmap;
import SIM.sim;
import javax.swing.*;
import java.util.Scanner;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
    private JLabel hiddenLabel;
    private JLabel initLabel;

    public MapForm(){

    }

    public MapManager EnterMapData(){
    setVisible(true);
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

        // 버튼을누르면 맵정보가 맵매니저로 전달.
        EnterData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String data = mapData.getText().toString();
                mapManager = new MapManager(data);
            }
        });

        return null;
    }

    public MapManager getMapManager(){
        return this.mapManager;
    }
}
