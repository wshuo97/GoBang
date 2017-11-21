package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Apex_WS on 2017/11/13.
 */
public class ServerMain {
    private static int number = 0;
    private static MyThread thread[] = new MyThread[2];
    private static DataOutputStream dos[] = new DataOutputStream[2];

    public static void main(String[] args) {
        new ServerMain().start();
    }

    public void start() {
        System.out.println("Server start...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8621);
            while (true) {
                if (number == 2) continue;
                Socket socket = serverSocket.accept();
                thread[number] = new MyThread(socket, number);
                thread[number].start();
                dos[number] = new DataOutputStream(socket.getOutputStream());
                System.out.println("The thread " + number + " is connecting.");
                number++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toClient(String text, int id) throws IOException {
        dos[id].writeChars(text);
    }

    public static void alterNumber(int id) {
        number--;
        thread[id] = null;
        dos[id] = null;
    }
}













