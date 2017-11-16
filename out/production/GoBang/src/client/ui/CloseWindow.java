package client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class CloseWindow implements ActionListener {
    private Window window;
    private boolean confirm = true;

    public CloseWindow(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!confirm || JOptionPane.showConfirmDialog(window, "Do you want to close this window?", "Are you sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
            window.dispose();
        }
    }
}
