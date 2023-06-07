package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.VBoxChoice;
import view.VBoxResults;

import java.io.FileNotFoundException;

public class Controller implements EventHandler {

    @Override
    public void handle(Event event) {
        VBoxChoice choice = view.HBoxMain.getChoice();
        VBoxResults results = view.HBoxMain.getResults();

        if(event.getSource() instanceof Button) {
            try {
                results.TableResults.getItems().clear();
                results.launchSimulations(choice.getSimulationsToDo());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
