package app.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://your-host.neon.tech/chatdb?sslmode=require";

    private static final String USER =
            "your_username";

    private static final String PASSWORD =
            "your_password";

    public static Connection getConnection() {

        try {

            Class.forName(
                    "org.postgresql.Driver"
            );

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch(Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}