package client.socket;

import client.logic.IPlayer;
import client.tools.Constant;
import client.ui.GameWindow;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Apex_WS on 2017/11/10.
 */
public class RealPlayer implements IPlayer {
    private int chess;
    private String myname;
    private String enemyname;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private GameWindow game;

    private String SERVER_IP = Constant.SERVER_IP;
    private int SERIAL = Constant.SERIAL;

    public RealPlayer(int chess, String name, GameWindow game) throws IOException {
        this.chess = chess;
        this.myname = name;
        this.game = game;
        socket = new Socket(SERVER_IP, SERIAL);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        String text = name + ":" + String.valueOf(chess) + "\n";
        ToServer.sendMsg(text, dos);
    }

    public boolean isWin(Point point, int type) {
        return true;
    }

    public boolean getWin() {
        return true;
    }

    public void searchNext() {
        try {
            String x, y;
            InputStreamReader isr = new InputStreamReader(dis);
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                x = "";
                y = "";
                String str = br.readLine();
                String item[] = str.split(":");
                if (item.length == 3) {
                    for (int i = 0; i < item[0].length(); i++)
                        if (Character.isDigit(item[0].charAt(i))) x += item[0].charAt(i);
                    for (int i = 0; i < item[1].length(); i++)
                        if (Character.isDigit(item[1].charAt(i))) y += item[1].charAt(i);
                    this.enemyname = item[2];
                    Point point = new Point(Integer.valueOf(x), Integer.valueOf(y));
                    game.iPlayerNext(point, this.chess);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChessPoint(Point point) {
        String text = String.valueOf(point.x) + ":" + String.valueOf(point.y) + "\n";
        try {
            ToServer.sendMsg(text, dos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goNext(Point poing, int chess) {
    }

    public String getMyName() {
        return this.myname;
    }

    public String getEnemyName() {
        return this.enemyname;
    }
}
