package client.part;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ModalWindow {
    static void winWindow(String winner) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();
        Text text = new Text(winner);
        pane.getChildren().add(text);

        Scene scene = new Scene(pane, 100, 100);
        stage.setScene(scene);
        stage.setTitle("Winning!!!");
        stage.showAndWait();
    }
}
