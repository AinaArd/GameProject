package client.part;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class GameSocketClient {
    private final static int PORT = 7878;
    private final static String IP = "127.0.0.1";

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    GameSocketClient() {
        startConnection(IP, PORT);
    }

    private void startConnection(String ip, int port) {
        try {
            Socket clientSocket = new Socket(ip, port);
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    void sendMessage(String msg) {
        printWriter.println(msg);
    }

    BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
