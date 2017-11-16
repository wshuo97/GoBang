package client.logic;

import java.awt.*;

import client.tools.Constant;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class ChessLogic {
    private static final int FLOOR_X = Constant.FLOOR_X;
    private static final int FLOOR_Y = Constant.FLOOR_Y;
    private static final int CEIL_X = Constant.CEIL_X;
    private static final int CEIL_Y = Constant.CEIL_Y;
    private static final int BLOCK = Constant.BLOCK;

    public static boolean is_in(int r, int c) {
        return r >= FLOOR_X && r <= CEIL_X && c >= FLOOR_Y && c <= CEIL_Y;
    }

    public static Point calculatePoint(Point point) {
        if (!is_in(point.x, point.y)) return new Point(-1, -1);
        int x = point.x - FLOOR_X, y = point.y - FLOOR_Y;
        int aimx = x / BLOCK * BLOCK + (x % BLOCK == 0 ? -17 : 18) + FLOOR_X, aimy = y / BLOCK * BLOCK + (y % BLOCK == 0 ? -17 : 18) + FLOOR_Y;
        if (aimx < 0) aimx += BLOCK;
        if (aimy < 0) aimy += BLOCK;
        return new Point(aimx, aimy);
    }

    public static int checkPoint(Point point) {
        int x = point.x, y = point.y;
        return ChessBoard.getChessSpot(x, y);
    }
}
