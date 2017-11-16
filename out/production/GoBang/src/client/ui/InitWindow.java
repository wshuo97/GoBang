package client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import com.intellij.uiDesigner.core.*;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class InitWindow extends JFrame {
    private JPanel panel;
    private JLabel model;
    private JLabel users;
    private JLabel chess;
    private JButton start;
    private JButton close;
    public static JTextArea names;
    private JRadioButton black;
    private JRadioButton white;
    private JRadioButton single;
    private JRadioButton online;

    public InitWindow() {
        //loading initial parts
        initFragment();
        close.addActionListener(new CloseWindow(this));
        setLocationRelativeTo(null);
    }

    private void startOnClicked(ActionEvent e) {
        //display game window
        GameWindow gameWindow = new GameWindow(single.isSelected(), black.isSelected());
        gameWindow.setVisible(true);
        dispose();
    }

    public static String getUserName() {
        return names.getText();
    }

    private void initFragment() {
        //create needed button
        panel = new JPanel();
        model = new JLabel();
        users = new JLabel();
        chess = new JLabel();
        start = new JButton();
        close = new JButton();
        names = new JTextArea();
        black = new JRadioButton();
        white = new JRadioButton();
        single = new JRadioButton();
        online = new JRadioButton();

        //setting window content
        setTitle("GoBang");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPanel = getContentPane();
        contentPanel.setLayout(null);

        //panel setting
        {
            panel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));

            //model text
            model.setText("Mode:");
            panel.add(model, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //single button
            single.setText("1P");
            single.setSelected(true);
            panel.add(single, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //online button
            online.setText("2P");
            panel.add(online, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //first or second
            chess.setText("Chess:");
            panel.add(chess, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //black chess & first
            black.setText("Black");
            black.setSelected(true);
            panel.add(black, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //white chess & second
            white.setText("White");
            panel.add(white, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //users text
            users.setText("User:");
            panel.add(users, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //input name text
            panel.add(names, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST,
                    GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //start button
            start.setText("Next");
            start.addActionListener(e -> {
                startOnClicked(e);
            });
            panel.add(start, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER,
                    GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //close button
            close.setText("Exit");
            close.setBounds(1, 1, 1, 1);
            panel.add(close, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
                    GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED, null, null, null));

            //group setting
            ButtonGroup group1 = new ButtonGroup();
            group1.add(single);
            group1.add(online);
            ButtonGroup group2 = new ButtonGroup();
            group2.add(black);
            group2.add(white);

            //display setting
            contentPanel.add(panel);
            panel.setBounds(10, 0, 203, 133);
            contentPanel.setPreferredSize(new Dimension(230, 140));
            pack();
            setLocationRelativeTo(getOwner());
        }
    }
}
















