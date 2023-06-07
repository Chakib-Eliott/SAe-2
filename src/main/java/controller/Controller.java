package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.VBoxChoice;
import view.VBoxResults;

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
                    results.TableResults.getItems().clear();
                    results.launchSimulations(choice.getSimulationsToDo());
                }
                if(Objects.equals(((Button) event.getSource()).getId(), "saveResults")){
                    System.out.println("save");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
