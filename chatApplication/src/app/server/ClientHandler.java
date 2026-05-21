package app.server;

import app.database.DBConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket socket){

        try{

            this.socket = socket;

            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            writer = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        try{

            String message;

            while((message = reader.readLine()) != null){

                saveMessage(message);

                broadcast(message);
            }

        }catch(Exception e){
            System.out.println(
                    "User disconnected"
            );
        }
    }

    private void broadcast(String message){

        for(ClientHandler client :
                ChatServer.clients){

            client.writer.println(message);
        }
    }

    private void saveMessage(String message){

        try{

            Connection conn =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO messages(username,message) VALUES(?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            String[] parts =
                    message.split(":",2);

            ps.setString(1, parts[0]);
            ps.setString(2, parts[1]);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}