package client.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Apex_WS on 2017/11/15.
 */
public class AppendLog {
    private static String filepath = Constant.ROOT_LOG;

    public static void checkFile() throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void appendToLog(String text) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        fw.write(text);
        fw.close();
    }
}
