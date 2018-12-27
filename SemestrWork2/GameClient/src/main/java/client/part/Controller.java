package client.part;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private GameSocketClient client;

    private String userId;

    @FXML
    private AnchorPane mainPane;

    private Button[][] buttons;

    public Button[][] getButtons() {
        return buttons;
    }

    @FXML
    public void initialize() {
        client = new GameSocketClient();
        buttons = new Button[3][3];
        List<Node> nodes = mainPane.getChildren();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int currentI = i;
                final int currentJ = j;
                buttons[i][j] = (Button) nodes.stream().filter(node -> node.getId().equals("button" + currentI + currentJ))
                        .collect(Collectors.toList()).get(0);
                buttons[i][j].setOnAction(event -> {
                    client.sendMessage(userId + "," + currentI + "," + currentJ);
                });
            }
        }
        ReceiverTask task = new ReceiverTask(this, client);
        ExecutorService executorService
                = Executors.newFixedThreadPool(1);
        executorService.execute(task);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }
}
