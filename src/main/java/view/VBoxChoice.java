package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import model.Solution;
import model.SolutionConstants;
import java.io.File;
import java.util.Optional;

public class VBoxChoice extends VBox {


    Simulation simulationsToDo[] = new Simulation[20];

    public VBoxChoice(int margin) {
        super(margin);

        // get all files names in "data" folder
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();


        // AJOUT DE SIMULATION

        ComboBox<String> scenarios = new ComboBox<>();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.endsWith(".txt")) {
                    // remove ".txt" from file name and replace "_" by " " and set upper case
                    String scenarioName = fileName.substring(0, fileName.length() - 4);
                    scenarioName = scenarioName.replace("_", " ");
                    scenarios.getItems().add(scenarioName);
                }
            }
        }

        scenarios.setValue(scenarios.getItems().get(0));


        // nombre de simulations (entier entre 1 et 20)
        ScrollBar nbSimulations = new ScrollBar();
        nbSimulations.setMin(1);
        nbSimulations.setMax(20);
        nbSimulations.setValue(1);
        nbSimulations.setBlockIncrement(1);
        nbSimulations.setPrefWidth(100);
        nbSimulations.setPrefHeight(20);
        nbSimulations.setUnitIncrement(1);

        // tics scroll avec la souris (1 par 1)
        // arrondir à l'entier le plus proche
        nbSimulations.valueProperty().addListener((observable, oldValue, newValue) ->
                nbSimulations.setValue(Math.round(newValue.doubleValue())));

        // affichage du nombre de simulations

        Label textNbSimulationsLabel = new Label("Nombre de simulations à ajouter :");

        Label nbSimulationsLabel = new Label("Nombre de simulations à ajouter :");


        nbSimulationsLabel.textProperty().bindBidirectional(nbSimulations.valueProperty(), new NumberStringConverter());
        nbSimulationsLabel.setPrefWidth(100);
        nbSimulationsLabel.setPrefHeight(20);


        // tableau des simulations
        TableView simulations = new TableView();


        TableColumn<Simulation, String> scenarioCol = new TableColumn<>("Scenario");
        TableColumn<Simulation, String> typeCol = new TableColumn<>("Type");

        scenarioCol.setCellValueFactory(new PropertyValueFactory<>("scenario"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        scenarioCol.setPrefWidth(100);
        typeCol.setPrefWidth(350);


        simulations.getColumns().addAll(scenarioCol, typeCol);

        // double clic sur une ligne
        simulations.setRowFactory(tv -> {
            TableRow<Simulation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    // ouvrir une fenêtre de choix de type de solution
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Choix du type de solution");
                    alert.setHeaderText(null);
                    alert.setGraphic(null);
                    alert.setContentText("Choisissez le type de solution");

                    for (String type : SolutionConstants.SOLUTIONS_TYPE.keySet()) {
                        alert.getDialogPane().getButtonTypes().add(new ButtonType(type));
                    }

                    // masquer les boutons OK et CANCEL
                    alert.getButtonTypes().remove(ButtonType.OK);
                    alert.getButtonTypes().remove(ButtonType.CANCEL);


                    Optional<ButtonType> result = alert.showAndWait();

                    // si l'utilisateur a choisi un type de solution
                    if (result.isPresent() && result.get().getButtonData() != ButtonBar.ButtonData.CANCEL_CLOSE) {
                        // récupérer le type de solution
                        String type = result.get().getText();
                        // récupérer la simulation
                        Simulation simulation = row.getItem();
                        // modifier le type de solution
                        simulation.setType(type);
                        // mettre à jour la ligne
                        simulations.refresh();
                        // récupérer le numéro de ligne sur lequel on a cliqué
                        int index = simulations.getItems().indexOf(simulation);
                        // mettre à jour le tableau des simulations à faire
                        simulationsToDo[index] = simulation;

                    }
                }
            });
            return row;
        });


        // choix du type de solution par défaut
        ComboBox<String> defaultType = new ComboBox<>();
        defaultType.getItems().add(null);
        defaultType.setPromptText("Type de solution");
        for (String type : SolutionConstants.SOLUTIONS_TYPE.keySet()) {
            defaultType.getItems().add(type);
        }

        // bouton d'ajout de simulation
        Button addSimulation = new Button("Ajouter simulation");
        addSimulation.setPrefWidth(200);
        addSimulation.setPrefHeight(20);
        addSimulation.setId("addSimulation");
        addSimulation.setOnAction(event -> {
            // récupérer le scénario sélectionné
            String scenario = scenarios.getValue();
            // récupérer le nombre de simulations
            int nb = (int) nbSimulations.getValue();
            // ajouter les simulations au tableau
            for (int i = 0; i < nb; i++) {
                if(defaultType.getValue() != null) {
                    simulations.getItems().add(new Simulation(scenario, defaultType.getValue()));
                    for (int j = 0; j <  simulationsToDo.length; j++) {
                        if (simulationsToDo[j] == null) {
                            simulationsToDo[j] = new Simulation(scenario, defaultType.getValue());
                            break;
                        }
                    }
                }
                else {
                    simulations.getItems().add(new Simulation(scenario));
                    for (int j = 0; j <  simulationsToDo.length; j++) {
                        if (simulationsToDo[j] == null) {
                            simulationsToDo[j] = new Simulation(scenario, defaultType.getValue());
                            break;
                        }
                    }
                }
            }
            System.out.println(simulationsToDo.length);
            int i = 0;
            for (Simulation simulation : simulationsToDo) {
                if (simulation != null) {
                    i++;
                }
            }
            System.out.println(i);
        });

        Label title = new Label("Choix de simulations");
        title.setId("title");

        // Lancer les simulations
        Button launchSimulations = new Button("Lancer les simulations");
        launchSimulations.setPrefWidth(200);
        launchSimulations.setPrefHeight(20);
        launchSimulations.setId("launchSimulations");
        // envoyer les simulations vers VBoxResults
        launchSimulations.setOnAction(new EventHandler<ActionEvent>() {
            // récupérer les simulations
//            ObservableList simulationsList = simulations.getItems();
            // event
            @Override
            public void handle(ActionEvent event) {
                // récupérer les simulations
//                System.out.println("test");
                addEventHandler(ActionEvent.ACTION, VBoxRoot.getController());
            }
        });

        launchSimulations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                launchSimulations.addEventHandler(ActionEvent.ACTION, VBoxRoot.getController());
            }

        });

        // bouton de suppression de toutes les simulations
        Button deleteSimulation = new Button("Supprimer toutes les simulations");
        deleteSimulation.setPrefWidth(200);
        deleteSimulation.setPrefHeight(20);
        deleteSimulation.setId("deleteSimulation");
        deleteSimulation.setOnAction(event -> {
            simulations.getItems().clear();
            for (int i = 0; i < simulationsToDo.length; i++) {
                simulationsToDo[i] = null;
            }
        });

        this.getChildren().addAll(
                title,
                scenarios,
                nbSimulations,
                textNbSimulationsLabel,
                nbSimulationsLabel,
                defaultType,
                addSimulation,
                simulations,
                launchSimulations, deleteSimulation
        );
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

        public String toString() {
            return "Scenario : " + scenario + " Type : " + type;
        }
    }

    public Simulation[] getSimulationsToDo() {
        return simulationsToDo;
    }
}
