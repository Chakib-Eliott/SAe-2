package view;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.*;
import utils.Parsing;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe de la fenêtre des résultats
 * @author Eliott-B, Chak
 * @see PropertyValueFactory
 * @see VBox
 * @see Parsing
 * @see File
 * @see FileNotFoundException
 * @see SolutionConstants
 */
public class VBoxResults extends VBox implements SolutionConstants {

    /**
     * Tableau des résultats
     */
    private TableView tableView;
    /**
     * Champ du nom de la sauvegarde
     */
    private TextField textField;
    /**
     * Label d'information sur la confirmation de la sauvegarde
     */
    private Label saveName;

    /**
     * Constructeur de la classe
     * @param margin int
     */
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

        Button save = new Button("_Sauvegarder les résultats");
        save.setPrefWidth(200);
        save.setPrefHeight(20);
        save.setId("saveResults");
        save.setOnAction(VBoxRoot.getController());
        this.getChildren().add(save);

        // Label vide pour avoir des confirmations après
        saveName = new Label("");
        this.getChildren().add(saveName);
    }

    /**
     * Retourne le nom de la sauvegarde
     * @return TextField
     */
    public TextField getTextField(){
        return textField;
    }

    /**
     * Change le message d'information de la sauvegarde
     * @param text String
     * @param id String (error / do)
     */
    public void setSaveName(String text, String id){
        saveName.setText(text);
        saveName.setId(id);
    }

    /**
     * Retourne la table des résultats
     * @return TableView
     */
    public TableView getTableView(){
        return tableView;
    }

    /**
     * Ajoute une solution aux résultats
     * @param item Solution
     */
    public void addItemToTableView(Solution item){
        tableView.getItems().add(item);
    }

    /**
     * Lance une simulation
     * @param simulationsToDo VBoxChoice.Simulation[]
     * @throws FileNotFoundException Erreur si le fichier n'existe pas
     */
    public void launchSimulations(VBoxChoice.Simulation[] simulationsToDo) throws FileNotFoundException, ExceptionSolution {

        for (VBoxChoice.Simulation simulation : simulationsToDo) {
            if (simulation == null) {
                continue;
            }
            Solution solution = null;
            for(String i : SOLUTIONS_TYPE.keySet()) {
                if (i.toUpperCase().contains("SPEED")) {
                    if(i.equals(simulation.getType())) {
                        solution = new Speedrun(
                                Parsing.parsing(
                                        new File(
                                                "data" + File.separator + simulation.getScenario().replace(" ", "_")+".txt"))
                                ,(int) SOLUTIONS_TYPE.get(i)[1]);
                    }
                }
                if (i.toUpperCase().contains("GLOUTON")) {
                    if(i.equals(simulation.getType())) {
                        solution = new Greedy(
                                Parsing.parsing(
                                        new File(
                                                "data" + File.separator + simulation.getScenario().replace(" ", "_")+".txt"))
                                ,(int) SOLUTIONS_TYPE.get(i)[1]);
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
