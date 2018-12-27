package server.part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameServer {
    private List<ClientHandler> players;

    public List<ClientHandler> getPlayers() {
        return players;
    }

    public String getUserId() {
        return userId;
    }

    private String userId = "1";

    public GameServer() {
        players = new CopyOnWriteArrayList<>();
    }

    void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        while (true) {
            try {

                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {

        private Socket clientSocket;
        private BufferedReader bufferedReader;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            players.add(this);
            System.out.println("Player added from port " + socket.getPort());
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                printWriter.println(userId);
                if (userId.equals("1")) {
                    userId = "2";
                } else {
                    userId = "1";
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }

        }

        public void run() {
            try {
                bufferedReader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    for (ClientHandler client : players) {
                        PrintWriter out = new PrintWriter(client.clientSocket.getOutputStream(), true);
                        out.println(line);
                    }
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
