module alrapa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires json.simple;

    opens alrapal to javafx.fxml;
    exports alrapal;
}