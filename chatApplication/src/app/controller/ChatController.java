package app.controller;

import app.client.ChatClient;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField messageField;

    private ChatClient client;

    @FXML
    public void initialize() {

        client = new ChatClient(
                message -> chatArea.appendText(
                        message + "\n"
                )
        );
    }

    @FXML
    public void sendMessage() {

        String username =
                usernameField.getText().trim();

        String message =
                messageField.getText().trim();

        if(username.isEmpty() ||
                message.isEmpty()){

            return;
        }

        String fullMessage =
                username + ": " + message;

        client.sendMessage(fullMessage);

        messageField.clear();
    }

}