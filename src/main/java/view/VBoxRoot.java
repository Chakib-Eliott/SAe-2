package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VBoxRoot extends VBox {

    public VBoxRoot(){
        super();

        MenuBar bar = new MenuBar();
        Menu quit = new Menu("Quitter");
        Menu scenario = new Menu("Scénarios");
        bar.getMenus().addAll(quit, scenario);
        this.getChildren().add(bar);

        HBox main = new HBoxMain();
        this.getChildren().add(main);

    }
}
