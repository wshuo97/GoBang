package client.socket;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Apex_WS on 2017/11/14.
 */
public class ToServer {
    public static void sendMsg(String context, DataOutputStream dos) throws IOException {
        dos.writeChars(context);
        System.out.println("Send msg is " + context);
    }
}
