package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe de la fenêtre principal en mode vertical
 * @author Eliott-B, Chak
 * @see Controller
 * @see ActionEvent
 * @see EventHandler
 * @see Clipboard
 * @see ClipboardContent
 * @see HBox
 * @see VBox
 * @see File
 */
public class VBoxRoot extends VBox {

    /**
     * Controlleur de l'application
     */
    private static final Controller controller = new Controller();
    /**
     * Menu des sauvegardes
     */
    private static Menu save;

    /**
     * Constructeur de la classe.
     */
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
                    MenuItem menuItemFile = new MenuItem(file.getName().replace(".ser",""));
                    menuItemFile.setUserData(file);
                    menuItemFile.setOnAction(VBoxRoot.getController());
                    save.getItems().add(menuItemFile);
                }
            }
        }

        // Menu quitter
        Menu info = new Menu("Info");
        MenuItem menuInfo = new MenuItem("Informations");
        info.getItems().add(menuInfo);
        // Action d'une boite de dialogue pour confirmer la fermeture
        menuInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Informations");
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setContentText("Cette application fait des simulations de parcours d'un RPG depuis des scénarios donnés." +
                        "\n\nAuteurs : Eliott-B, Chak\nAnnée : 2023\nGit : https://github.com/Chakib-Eliott/SAe-2/\n\n" +
                        "*Cliquez sur OUI si vous voulez copier le lien GitHub.*");
                // Retire les boutons par default et met YES et OK
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.OK);
                // Met en présélectionné YES
                Button yes = (Button) alert.getDialogPane().lookupButton( ButtonType.YES );
                yes.setDefaultButton( true );

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get() == ButtonType.YES){
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString("https://github.com/Chakib-Eliott/SAe-2/");
                    clipboard.setContent(content);
                }
            }
        });

        bar.getMenus().addAll(quit, save, info);
        this.getChildren().add(bar);

        HBox main = new HBoxMain(5);
        this.getChildren().add(main);

    }

    /**
     * Ajoute une sauvegarde dans le menu
     * @param item MenuItem
     */
    public static void addSave(MenuItem item){
        item.setOnAction(VBoxRoot.getController());
        save.getItems().add(item);
    }

    /**
     * Retourne le menu de sauvegarde
     * @return Menu
     */
    public static Menu getSave(){
        return save;
    }

    /**
     * Retourne le controlleur
     * @return Controller
     */
    public static Controller getController() {
        return controller;
    }
}
