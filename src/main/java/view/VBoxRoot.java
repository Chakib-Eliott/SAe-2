package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class VBoxRoot extends VBox {
    private static final Controller controller = new Controller();
    private static Menu save;

    public VBoxRoot(){
        super();

        MenuBar bar = new MenuBar();
        // Menu quitter
        Menu quit = new Menu("Quitter");
        MenuItem menuItem = new MenuItem("Quitter la simulation");
        quit.getItems().add(menuItem);
        // Action d'une boite de dialogue pour confirmer la fermeture
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Quitter la simulation ?");
                alert.setHeaderText("Êtes-vous sûr de vouloir quitter l'application ?");
                alert.setContentText("Les solutions peuvent être longues à relancer.");
                // Retire les boutons par default et met CANCEL et CLOSE
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.CLOSE);
                // Met en présélectionné CANCEL
                Button cancelButton = (Button) alert.getDialogPane().lookupButton( ButtonType.CANCEL );
                cancelButton.setDefaultButton( true );

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get() == ButtonType.CLOSE){
                    System.exit(0);
                }
            }
        });

        // Menu des sauvegardes
        save = new Menu("Sauvegardes");
        // Récupère tous les fichiers dans results
        File dir = new File("results");
        File [] files = dir.listFiles();
        if(files != null){
            for(File file : files){
                // Vérifie que ce n'est pas un fichier caché MacOS (._file)
                if(!Objects.equals(String.format("%." + 2 + "s", file.getName()), "._")){
                    RadioMenuItem radioMenuItem = new RadioMenuItem(file.getName().replace(".ser",""));
                    save.getItems().add(radioMenuItem);
                }
            }
        }

        bar.getMenus().addAll(quit, save);
        this.getChildren().add(bar);

        HBox main = new HBoxMain(5);
        this.getChildren().add(main);

    }

    public static void addSave(RadioMenuItem item){
        save.getItems().add(item);
    }

    public static Menu getSave(){
        return save;
    }

    public static Controller getController() {
        return controller;
    }
}
