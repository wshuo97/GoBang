package client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import client.ui.InitWindow;
import client.tools.AppendLog;
import sun.plugin2.util.AppletEnumeration;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class ClientMain {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        try {
            AppendLog.checkFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventQueue.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new InitWindow();
                frame.setVisible(true);
            }
        });
    }
}
