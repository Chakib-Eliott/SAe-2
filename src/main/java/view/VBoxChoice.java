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

    public static class Simulation {
        private final String scenario;
        private String type = "Cliquez pour définir le type de solution";

        public Simulation(String scenario) {
            this.scenario = scenario;
        }

        public Simulation(String scenario, String type) {
            this.scenario = scenario;
            this.type = type;
        }

        public String getScenario() {
            return scenario;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
