package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class VBoxRoot extends VBox {

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
                // Retire les boutons par défault et met CANCEL et CLOSE
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.CLOSE);
                // Met en préselectionné CANCEL
                Button cancelButton = (Button) alert.getDialogPane().lookupButton( ButtonType.CANCEL );
                cancelButton.setDefaultButton( true );

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get() == ButtonType.CLOSE){
                    System.exit(0);
                }
            }
        });

        // Menu des scénarios
        Menu scenario = new Menu("Scénarios");
        bar.getMenus().addAll(quit, scenario);
        this.getChildren().add(bar);

        HBox main = new HBoxMain();
        this.getChildren().add(main);

    }
}
