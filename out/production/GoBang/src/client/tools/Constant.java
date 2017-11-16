package client.tools;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class Constant {
    //chess board constants
    public static final int BLOCK = 35;
    public static final int SPOT_UP = 22;
    public static final int SPOT_LEFT = 22;
    public static final int FLOOR_X = 4;
    public static final int FLOOR_Y = 4;
    public static final int UP_EPS = 20;
    public static final int DOWN_EPS = 17;
    public static final int LEFT_EPS = 20;
    public static final int RIGHT_EPS = 17;
    public static final int CEIL_X = 529;
    public static final int CEIL_Y = 529;
    public static final int ROW_NUM = 15;
    public static final int COLUMN_NUM = 15;
    public static final int CHESS_WIDTH = 40;
    public static final int CHESS_HEIGHT = 40;

    //direction control
    public static final int dx[] = {0, 35, 35, -35};
    public static final int dy[] = {35, 0, 35, 35};

    //net about
    public static final String SERVER_IP = "192.168.134.120";
    //public static final String SERVER_IP="115.159.184.121";
    public static final int SERIAL = 8621;

    //file path
    public static final String ROOT_LOG = "./log.txt";
}
