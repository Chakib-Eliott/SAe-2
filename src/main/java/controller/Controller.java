package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableView;
import utils.ReadWrite;
import view.HBoxMain;
import view.VBoxChoice;
import view.VBoxResults;
import view.VBoxRoot;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Controller implements EventHandler {

    @Override
    public void handle(Event event) {
        VBoxChoice choice = HBoxMain.getChoice();
        VBoxResults results = HBoxMain.getResults();

        if(event.getSource() instanceof Button) {
            try {
                if(Objects.equals(((Button) event.getSource()).getId(), "launchSimulations")){
                    results.tableView.getItems().clear();
                    results.launchSimulations(choice.getSimulationsToDo());
                }
                if(Objects.equals(((Button) event.getSource()).getId(), "saveResults")) {
                    if (results.getTextField().getText().equals("")) {
                        results.setSaveName("Vous devez rentrer un nom pour la sauvegarde !","error");
                    } else if (results.getTableView().getItems().size() == 0) {
                        results.setSaveName("Vous n'avez pas fait de simulation.","error");
                    }else{
                        // Récupère un fichier avec le nom qu'on a mit
                        File resultsFile = new File("results"+File.separator+results.getTextField().getText()+".ser");
                        TableView tableView = results.getTableView();
                        for(Object elt : tableView.getItems()){
                            ReadWrite.write(resultsFile, elt);
                        }
                        RadioMenuItem radioMenuItem = new RadioMenuItem(results.getTextField().getText());
                        VBoxRoot.addSave(radioMenuItem);
                        results.setSaveName("Vos simulations ont bien été sauvegardées !","do");
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
