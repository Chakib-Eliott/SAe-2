package view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class VBoxChoice extends VBox {

    public VBoxChoice() {
        super();

        Label title = new Label("Choix de simulations");
        this.getChildren().add(title);
    }
}
