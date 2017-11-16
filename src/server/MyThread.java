package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;

/**
 * Created by Apex_WS on 2017/11/14.
 */
public class MyThread extends Thread {
    private int id;
    private String name;
    private Socket socket = null;
    private static DataInputStream dis;

    public MyThread(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            getUserName();
            InputStreamReader isr = new InputStreamReader(dis);
            BufferedReader bf = new BufferedReader(isr);
            String x = "", y = "";
            while (true) {
                String str = bf.readLine();
                String item[] = str.split(":");
                if (item.length == 2) {
                    System.out.println(this.name + " send point is " + item[0] + " " + item[1]);
                    for (int i = 0; i < item[0].length(); i++)
                        if (Character.isDigit(item[0].charAt(i))) x += item[0].charAt(i);
                    for (int i = 0; i < item[1].length(); i++)
                        if (Character.isDigit(item[1].charAt(i))) y += item[1].charAt(i);
                    String text = x + ":" + y + ":" + this.name + "\n";
                    sendToClient(text, id ^ 1);
                    x = "";
                    y = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ServerMain.alterNumber(id);
        }
    }

    private void sendToClient(String text, int idnumber) throws IOException {
        ServerMain.toClient(text, idnumber);
    }

    public void getUserName() throws IOException {
        InputStreamReader isr = new InputStreamReader(dis);
        BufferedReader bf = new BufferedReader(isr);
        while (true) {
            String str = bf.readLine();
            String item[] = str.split(":");
            if (item.length == 2) {
                this.name = item[0];
                System.out.println("The user name is " + this.name);
                break;
            }
        }
    }
}

















