package client.logic;

import java.awt.*;
import java.util.Random;

import client.tools.Constant;
import client.ui.GameWindow;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class AiPlayer implements IPlayer {
    private static int chess;
    private static int BLOCK = Constant.BLOCK;
    private static int dx[] = Constant.dx;
    private static int dy[] = Constant.dy;
    private static boolean win;
    private String myname;
    private String enemyname;
    private GameWindow game;

    public AiPlayer(int chess, String name, GameWindow game) {
        this.chess = chess;
        this.game = game;
        this.myname = name;
        this.enemyname = "computer";
    }

    public boolean isWin(Point point, int type) {
        return win;
    }

    public void searchNext() {
        Random random = new Random();
        int x = 4 + 18 + Math.abs(random.nextInt()) % 15 * BLOCK, y = 4 + 18 + Math.abs(random.nextInt()) % 15 * BLOCK;
        while (ChessBoard.getChessSpot(x, y) != -1) {
            x = 4 + 18 + Math.abs(random.nextInt()) % 15 * BLOCK;
            y = 4 + 18 + Math.abs(random.nextInt()) % 15 * BLOCK;
        }
        Point point = new Point(x, y);
        //System.out.println(point);
        game.iPlayerNext(point, this.chess);
    }

    public void goNext(Point poing, int chess) {
    }

    public void sendChessPoint(Point point) {

    }

    public boolean getWin() {
        return win;
    }

    public String getMyName() {
        return this.myname;
    }

    public String getEnemyName() {
        return this.enemyname;
    }
}
