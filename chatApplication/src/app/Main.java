package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "resources/chat.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        // Load CSS
        scene.getStylesheets().add(
                getClass()
                        .getResource("resources/style.css")
                        .toExternalForm()
        );

        stage.setTitle("Java Chat Application");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}