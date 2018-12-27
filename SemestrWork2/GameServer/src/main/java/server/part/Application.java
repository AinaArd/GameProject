package server.part;

public class Application {
    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.start(7878);
    }
}
