module ec.edu.espol.grupo_07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.grupo_07 to javafx.fxml;
    exports ec.edu.espol.grupo_07;
    opens ec.edu.espol.controller to javafx.fxml;
    exports ec.edu.espol.controller;
}
