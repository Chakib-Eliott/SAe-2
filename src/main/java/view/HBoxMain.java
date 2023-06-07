package view;

import controller.Controller;
import javafx.scene.layout.HBox;

public class HBoxMain extends HBox {

    private static VBoxChoice choice;
    private static VBoxResults results;

    public HBoxMain(int margin) {
        super(margin);

        // VBox des choix
        choice = new VBoxChoice(10);
        // VBox des résultats
        results = new VBoxResults(10);

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
}
