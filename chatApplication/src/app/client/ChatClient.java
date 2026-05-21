package app.client;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public class ChatClient {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    // Used to update GUI when messages arrive
    private Consumer<String> messageListener;

    public ChatClient(Consumer<String> messageListener) {

        this.messageListener = messageListener;

        try {

            socket = new Socket("localhost", 5000);

            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            writer = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            receiveMessages();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Send message to server
    public void sendMessage(String message) {
        writer.println(message);
    }

    // Receive messages from server
    private void receiveMessages() {

        Thread thread = new Thread(() -> {

            try {

                String message;

                while ((message = reader.readLine()) != null) {

                    String received = message;

                    Platform.runLater(() ->
                            messageListener.accept(received)
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        thread.setDaemon(true);
        thread.start();
    }
}