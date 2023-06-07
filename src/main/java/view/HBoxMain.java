package view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxMain extends HBox {

    public HBoxMain() {
        super();

        // VBox des choix
        VBox choice = new VBoxChoice();
        // VBox des résultats
        VBox results = new VBoxResults();

        choice.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        results.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        this.getChildren().addAll(choice, results);
    }
}
