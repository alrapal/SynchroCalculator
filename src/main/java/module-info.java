module alrapa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.controlsfx.controls;

    opens alrapal to javafx.fxml;
    exports alrapal;
}