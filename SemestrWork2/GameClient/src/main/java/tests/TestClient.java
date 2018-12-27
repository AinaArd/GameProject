package tests;

import client.part.Controller;
import client.part.ModalWindow;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TestClient {

    @Test
    public void checkIfStage() {
        Controller controller = new Controller();
        assertNotNull(controller.getButtons());
    }

}
