package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;

public class VBoxResults extends VBox {

    public TableView tableView;
    private TextField textField;
    private Label errorName;

    public VBoxResults(int margin) {
        super(margin);

        Label title = new Label("Résultats des simulations");
        title.setId("title");
        this.getChildren().add(title);

        tableView = new TableView();
        // tableau des résultats
        tableView.setPrefSize(500, 550);

        // scénario, type, durée, nombre de quêtes, déplacements, xp
        TableColumn<VBoxChoice.Simulation, String> scenarioColumn = new TableColumn<>("Scenario");
        TableColumn<VBoxChoice.Simulation, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Solution, Integer> durationColumn = new TableColumn<>("Durée");
        TableColumn<Solution, Integer> nbQuestColumn = new TableColumn<>("Quêtes");
        TableColumn<Solution, Integer> travelsColumn = new TableColumn<>("Déplacements");
        TableColumn<Solution, Integer> xpColumn = new TableColumn<>("Xp");

        scenarioColumn.setCellValueFactory(new PropertyValueFactory<>("scenario"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        nbQuestColumn.setCellValueFactory(new PropertyValueFactory<>("nbQuests"));
        travelsColumn.setCellValueFactory(new PropertyValueFactory<>("travel"));
        xpColumn.setCellValueFactory(new PropertyValueFactory<>("xp"));

        scenarioColumn.setPrefWidth(65);
        typeColumn.setPrefWidth(200);
        durationColumn.setPrefWidth(50);
        xpColumn.setPrefWidth(50);
        nbQuestColumn.setPrefWidth(50);
        travelsColumn.setPrefWidth(100);

        tableView.getColumns().addAll(
                scenarioColumn,
                typeColumn,
                durationColumn,
                nbQuestColumn,
                travelsColumn,
                xpColumn
        );

        this.getChildren().add(tableView);

        // FORMUMAIRE DE SAUVEGARDE
        Label name = new Label("Nom de la sauvegarde");
        textField = new TextField();
        this.getChildren().addAll(name, textField);

        Button save = new Button("Sauvegarder les résultats");
        save.setPrefWidth(200);
        save.setPrefHeight(20);
        save.setId("saveResults");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                save.addEventHandler(ActionEvent.ACTION, HBoxMain.getController());
            }
        });
        this.getChildren().add(save);

        // ERROR si pas de nom entré
        errorName = new Label("");
        errorName.setId("error");
        this.getChildren().add(errorName);
    }

    public TextField getTextField(){
        return textField;
    }

    public void setErrorName(String text){
        errorName.setText(text);
    }

    public TableView getTableView(){
        return tableView;
    }

    public void launchSimulations(VBoxChoice.Simulation[] simulationsToDo) throws FileNotFoundException {

        for (VBoxChoice.Simulation simulation : simulationsToDo) {
            if (simulation == null) {
                continue;
            }
            Solution solution = null;
            for(String i : SolutionConstants.SOLUTIONS_TYPE.keySet()) {
                if (i.toUpperCase().contains("SPEED")) {
                    if(i.equals(simulation.getType())) {
                        solution = new Speedrun(
                                Parsing.parsing(
                                        new File(
                                                "data/" + simulation.getScenario().replace(" ", "_")+".txt"))
                                ,(int) SolutionConstants.SOLUTIONS_TYPE.get(i)[1]);
                    }
                }
                if (i.toUpperCase().contains("GLOUTON")) {
                    if(i.equals(simulation.getType())) {
                        solution = new Greedy(
                                Parsing.parsing(
                                        new File(
                                                "data/" + simulation.getScenario().replace(" ", "_")+".txt"))
                                ,(int) SolutionConstants.SOLUTIONS_TYPE.get(i)[1]);
                    }
                }
            }
            if (solution != null) {

                solution.setName(simulation.getType());
                tableView.getItems().add(solution);

            }

        }
    }
}
