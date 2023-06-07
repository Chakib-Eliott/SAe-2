package view;

import controller.Controller;
import javafx.scene.layout.HBox;

public class HBoxMain extends HBox {

public static VBoxChoice choice = new VBoxChoice();
public static VBoxResults results = new VBoxResults();
public static final Controller controller = new Controller();
    public HBoxMain() {
        super();

        // VBox des choix
        choice = new VBoxChoice();
        // VBox des résultats
        results = new VBoxResults();

        choice.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        results.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        this.getChildren().addAll(choice, results);
    }

    public static VBoxChoice getChoice() {
        return choice;
    }

    public static VBoxResults getResults() {
        return results;
    }

    public static Controller getController() {
        return controller;
    }
}
