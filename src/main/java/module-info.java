module com.espol.proyecto2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.espol.proyecto2 to javafx.fxml;
    exports com.espol.proyecto2;
}
