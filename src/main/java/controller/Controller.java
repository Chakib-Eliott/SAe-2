package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.VBoxChoice;
import view.VBoxResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Controller implements EventHandler {

    @Override
    public void handle(Event event) {
        VBoxChoice choice = view.HBoxMain.getChoice();
        VBoxResults results = view.HBoxMain.getResults();

        if(event.getSource() instanceof Button) {
            try {
                if(Objects.equals(((Button) event.getSource()).getId(), "launchSimulations")){
                    results.tableView.getItems().clear();
                    results.launchSimulations(choice.getSimulationsToDo());
                }
                if(Objects.equals(((Button) event.getSource()).getId(), "saveResults")){
                    if(results.getTextField().getText().equals("")){
                        results.setErrorName("Vous devez rentrer un nom pour la sauvegarde !");
                    }else{
                        results.setErrorName("");
                        // Récupère un fichier avec le nom qu'on a mit
                        File resultsFile = new File("results"+File.separator+results.getTextField().getText());
                        //ReadWrite.write(resultsFile, );
                    }

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
