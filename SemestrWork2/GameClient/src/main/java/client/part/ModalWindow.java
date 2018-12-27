package client.part;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ModalWindow {
    public static void winWindow(String winner) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();
        Label text = new Label(winner);
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 100, 100);
        stage.setScene(scene);
        stage.setTitle("Winning!!!");
        stage.showAndWait();
    }
}
