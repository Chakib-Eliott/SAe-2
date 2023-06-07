package view;

import javafx.scene.layout.HBox;

/**
 * Classe de HBoxMain, fenêtre principale (sans le menu)
 * @author Eliott-B, Chak
 * @see HBox
 */
public class HBoxMain extends HBox {

    /**
     * Fenêtre de choix
     */
    private static VBoxChoice choice;
    /**
     * Fenêtre des résultats
     */
    private static VBoxResults results;

    /**
     * Constructeur de la classe
     * @param margin int
     */
    public HBoxMain(int margin) {
        super(margin);

        // VBox des choix
        choice = new VBoxChoice(10);
        // VBox des résultats
        results = new VBoxResults(10);

        choice.prefWidthProperty().bind(this.widthProperty().multiply(0.5));
        results.prefWidthProperty().bind(this.widthProperty().multiply(0.5));

        this.getChildren().addAll(choice, results);
    }

    /**
     * Retourne la fenêtre de choix
     * @return VBoxChoice
     */
    public static VBoxChoice getChoice() {
        return choice;
    }

    /**
     * Retourne la fenêtre des résultats
     * @return VBoxResults
     */
    public static VBoxResults getResults() {
        return results;
    }
}
