package client.part;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;

import java.io.IOException;

public class ReceiverTask extends Task<Void> {

    private Controller controller;
    private GameSocketClient client;


    ReceiverTask(Controller controller, GameSocketClient client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    protected Void call() {
        while (true) {
            try {
                if (client.getBufferedReader().ready()) {
                    String response = client.getBufferedReader().readLine();
                    if (response != null) {
                        System.out.println(response);
                        if (response.length() == 1) {
                            controller.setUserId(response);
                        } else {
                            String userId = response.split(",")[0];
                            int buttonRow = Integer.parseInt(response.split(",")[1]);
                            int buttonColumn = Integer.parseInt(response.split(",")[2]);

                            Platform.runLater(() -> {
                                if (userId.equals("1")) {
                                    controller.getButtons()[buttonRow][buttonColumn].setText(String.valueOf('x'));
                                    if (checkWinner('x')) {
                                        System.out.println("X won");
                                        ModalWindow.winWindow("X won");
                                        restart();
                                    }
                                } else {
                                    controller.getButtons()[buttonRow][buttonColumn].setText(String.valueOf('o'));
                                    if (checkWinner('o')) {
                                        System.out.println("O won");
                                        ModalWindow.winWindow("O won");
                                        restart();
                                    }
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private void restart() {
        //TODO: restart
    }

    private boolean checkWinner(char sign) {
        Button[][] buttons = controller.getButtons();
        if (buttons[0][0].getText().equals(String.valueOf(sign))) {
            if (buttons[0][1].getText().equals(String.valueOf(sign))) {
                if (buttons[0][2].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            } else if (buttons[1][0].getText().equals(String.valueOf(sign))) {
                if (buttons[2][0].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            } else if (buttons[1][1].getText().equals(String.valueOf(sign))) {
                if (buttons[2][2].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            }
        } else if (buttons[1][0].getText().equals(String.valueOf(sign))) {
            if (buttons[1][1].getText().equals(String.valueOf(sign))) {
                if (buttons[1][2].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            }
        } else if (buttons[2][0].getText().equals(String.valueOf(sign))) {
            if (buttons[2][1].getText().equals(String.valueOf(sign))) {
                if (buttons[2][2].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            }
        } else if (buttons[0][1].getText().equals(String.valueOf(sign))) {
            if (buttons[1][1].getText().equals(String.valueOf(sign))) {
                if (buttons[2][1].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            }
        } else if (buttons[0][2].getText().equals(String.valueOf(sign))) {
            if (buttons[1][1].getText().equals(String.valueOf(sign))) {
                if (buttons[2][0].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            } else if (buttons[1][2].getText().equals(String.valueOf(sign))) {
                if (buttons[2][2].getText().equals(String.valueOf(sign))) {
                    System.out.println("Game is over");
                    return true;
                }
            }
        }
        System.out.println("Still playing");
        return false;
    }
}
