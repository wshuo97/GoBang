package client.tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Apex_WS on 2017/11/9.
 */
public class AssetsTool {

    public static Image getImage(String name) {
        try (InputStream inputStream = AssetsTool.class.getResourceAsStream("/"+name)) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon getIcon(String name) {
        return new ImageIcon(getImage(name));
    }
}
