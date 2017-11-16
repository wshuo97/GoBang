package client.logic;

import java.awt.*;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public interface IPlayer {
    public void searchNext();

    public void goNext(Point point, int chess);

    public void sendChessPoint(Point point);

    public boolean isWin(Point point, int type);

    public boolean getWin();

    public String getMyName();

    public String getEnemyName();
}
