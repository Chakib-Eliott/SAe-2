package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;

public class RPGapp extends Application {

    public void start(Stage stage) {
        HBox root = new HBoxRoot();

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);

        stage.setTitle("RPG Simulation");
        stage.centerOnScreen();

        File css = new File("asset"+ File.separator+"style.css");
        scene.getStylesheets().add(css.toURI().toString());

        stage.setResizable(false);

        stage.show();
    }

    public static void main(String [] args) {
        Application.launch();
    }
}
