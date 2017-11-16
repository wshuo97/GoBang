package client.ui;

import client.logic.AiPlayer;
import client.logic.ChessBoard;
import client.logic.IPlayer;
import client.socket.RealPlayer;
import client.tools.AppendLog;
import client.tools.AssetsTool;
import client.logic.ChessLogic;
import client.tools.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class GameWindow extends JFrame {
    private static JPanel panel;
    private int model;
    private int chess;
    private static int round;
    private static BufferedImage image;
    private static IPlayer enemy[] = new IPlayer[2];
    private static Thread thread = null;

    private static final int UP_EPS = Constant.UP_EPS;
    private static final int LEFT_EPS = Constant.LEFT_EPS;
    private static final int WIDTH = Constant.CHESS_WIDTH;
    private static final int HEIGHT = Constant.CHESS_HEIGHT;
    private static final int dx[] = Constant.dx;
    private static final int dy[] = Constant.dy;
    private static final Image BLACK = AssetsTool.getImage("black.gif");
    private static final Image WHITE = AssetsTool.getImage("white.gif");

    public GameWindow(boolean model, boolean chess) {
        this.model = (model ? 0 : 1);
        this.chess = (chess ? 1 : 0);

        image = (BufferedImage) AssetsTool.getImage("board.jpg");
        ChessBoard.clearBoard();
        setResizable(false);
        setTitle("Gobang by WangShuo");
        setSize(540, 568);
        createEnemyPlayer(this.model);

        panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (round != 1) return;
                Point aimpoint = ChessLogic.calculatePoint(e.getPoint());
                if (aimpoint.x == -1) return;
                if (ChessLogic.checkPoint(aimpoint) != -1) return;
                mouseOnClicked(aimpoint);
            }
        });

        startGame();

        getContentPane().add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createEnemyPlayer(int model) {
        if (model == 0) {
            enemy[model] = new AiPlayer((this.chess + 1) % 2, InitWindow.getUserName(), this);
            round = this.chess;
        } else {
            try {
                enemy[model] = new RealPlayer((this.chess + 1) % 2, InitWindow.getUserName(), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            round = this.chess;
        }
    }

    private void startGame() {
        ChessBoard.clearBoard();
        if (this.model == 1) {
            thread = new Thread() {
                public void run() {
                    enemy[model].searchNext();
                }
            };
        }
        panel.repaint();
    }

    public static int getNowRound() {
        return round;
    }

    public static void alterRound() {
        round = (round + 1) % 2;
    }

    private void mouseOnClicked(Point point) {
        drawChess(image, point, chess);
        panel.repaint();
        enemy[model].sendChessPoint(point);
        if (hasWin(point, chess)) {
            thread = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String text = enemy[model].getMyName() + " " + (df.format(new Date()).toString()) + "\r\n";
            try {
                AppendLog.appendToLog(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (JOptionPane.showConfirmDialog(this, "You're not garbage", "You win, go on ?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                image = (BufferedImage) AssetsTool.getImage("board.jpg");
                startGame();
            } else {
                dispose();
            }
            return;
        }
        alterRound();
        if (this.model == 0) enemy[model].searchNext();
    }

    public void iPlayerNext(Point point, int type) {
        drawChess(image, point, type);
        panel.repaint();
        if (hasWin(point, type)) {
            thread = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String text = enemy[model].getEnemyName() + " " + (df.format(new Date()).toString()) + "\r\n";
            try {
                AppendLog.appendToLog(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (JOptionPane.showConfirmDialog(this, "You are garbage", "You lose, go on ?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                image = (BufferedImage) AssetsTool.getImage("board.jpg");
                startGame();
            } else {
                dispose();
            }
            return;
        }
        alterRound();
    }

    private static void drawChess(BufferedImage image, Point point, int chess) {
        int aimx = point.x - LEFT_EPS, aimy = point.y - UP_EPS;
        Graphics2D g = image.createGraphics();
        ChessBoard.setSpotFull(point, chess);
        if (chess == 1) {
            g.drawImage(BLACK, aimx, aimy, WIDTH, HEIGHT, null);
        } else if (chess == 0) {
            g.drawImage(WHITE, aimx, aimy, WIDTH, HEIGHT, null);
        }
        g.dispose();
    }

    private boolean hasWin(Point point, int type) {
        int x = point.x, y = point.y;
        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i], cy = y + dy[i];
            int num = 1;
            while (ChessLogic.is_in(cx, cy) && ChessBoard.getChessSpot(cx, cy) == type) {
                cx += dx[i];
                cy += dy[i];
                num++;
            }
            cx = x - dx[i];
            cy = y - dy[i];
            while (ChessLogic.is_in(cx, cy) && ChessBoard.getChessSpot(cx, cy) == type) {
                cx -= dx[i];
                cy -= dy[i];
                num++;
            }
            if (num >= 5) return true;
        }
        return false;
    }
}
























