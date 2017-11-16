package client.logic;

import client.tools.Constant;

import java.awt.*;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class ChessBoard {
    private static int used[][] = new int[550][550];
    private static final int SPOT_UP = Constant.SPOT_UP;
    private static final int SPOT_LEFT = Constant.SPOT_LEFT;
    private static final int CEIL_X = Constant.CEIL_X;
    private static final int CEIL_Y = Constant.CEIL_Y;
    private static final int BLOCK = Constant.BLOCK;

    public static void clearBoard() {
        for (int i = SPOT_LEFT; i < CEIL_X; i += BLOCK) {
            for (int j = SPOT_UP; j < CEIL_Y; j += BLOCK) {
                used[i][j] = -1;
            }
        }
    }

    public static void setSpotFull(Point point,int type) {
        used[point.x][point.y]=type;
    }

    public static int getChessSpot(int x, int y) {
        return used[x][y];
    }
}
