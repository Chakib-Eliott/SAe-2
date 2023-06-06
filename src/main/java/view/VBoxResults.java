package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VBoxResults extends VBox {

    public VBoxResults() {
        super();

        Label title = new Label("Résultats des simulations");
        this.getChildren().add(title);
    }
}
