package app.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    // Stores connected users
    public static ArrayList<ClientHandler> clients =
            new ArrayList<>();

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket =
                    new ServerSocket(5000);

            System.out.println("Server started...");

            while(true){

                Socket socket =
                        serverSocket.accept();

                System.out.println(
                        "New user connected"
                );

                ClientHandler client =
                        new ClientHandler(socket);

                clients.add(client);

                Thread thread =
                        new Thread(client);

                thread.start();
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}