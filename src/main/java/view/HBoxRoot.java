package view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxRoot extends HBox {

    public HBoxRoot(){
        super();
        // VBox des choix
        VBox choice = new VBoxChoice();
        // VBox des résultats
        VBox results = new VBoxResults();

        this.getChildren().addAll(choice, results);
    }
}
