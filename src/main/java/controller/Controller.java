package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import model.Solution;
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
                    results.getTableView().getItems().clear();
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
                        // Vérifie que le fichier n'est pas déjà dans le menu
                        boolean contain = false;
                        for(MenuItem item : VBoxRoot.getSave().getItems()){
                            if(Objects.equals(item.getText(), results.getTextField().getText())){
                                contain = true;
                            }
                        }
                        if(!contain){
                            MenuItem menuItemFile = new MenuItem(results.getTextField().getText());
                            menuItemFile.setUserData(resultsFile);
                            VBoxRoot.addSave(menuItemFile);
                        }
                        results.setSaveName("Vos simulations ont bien été sauvegardées !","do");
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(event.getSource() instanceof MenuItem){
            results.getTableView().getItems().clear();
            System.out.println(((MenuItem)event.getSource()).getUserData());
            Solution solutions = (Solution) ReadWrite.read(((File)((MenuItem)event.getSource()).getUserData()));
            results.addItemToTableView(solutions);
        }
    }
}
