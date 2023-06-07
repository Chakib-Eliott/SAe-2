module com.example.ihm {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.ihm to javafx.fxml;
    exports com.example.ihm;
    exports model;
    exports view;
    exports utils;
    exports controller;
}