package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RPGapp extends Application {

    public void start(Stage stage) {
        HBox root = new HBoxRoot();

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);

        stage.setTitle("RPG Simulation");
        stage.centerOnScreen();

        stage.setResizable(false);

        stage.show();
    }

    public static void main(String [] args) {
        Application.launch();
    }
}
