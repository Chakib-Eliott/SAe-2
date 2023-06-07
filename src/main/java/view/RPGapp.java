package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;

/**
 * Classe de l'application
 * @author Eliott-B, Chak
 * @see Application
 * @see Scene
 * @see VBox
 * @see Stage
 * @see File
 * @see Image
 */
public class RPGapp extends Application {

    /**
     * Paramètre l'application
     * @param stage Stage
     */
    public void start(Stage stage) {
        VBox root = new VBoxRoot();

        Scene scene = new Scene(root, 1050, 750);
        stage.setScene(scene);

        stage.setTitle("RPG Simulation");
        stage.centerOnScreen();
        // Ajout d'un logo à l'application
        stage.getIcons().add(new Image("https://i.ibb.co/HY2S8P3/rpg-logo.png"));

        // Ajout d'une feuille de style
        File css = new File("asset"+ File.separator+"style.css");
        scene.getStylesheets().add(css.toURI().toString());

        stage.setResizable(false);

        stage.show();
    }

    /**
     * Lance l'application
     * @param args String []
     */
    public static void main(String [] args) {
        Application.launch();
    }
}
