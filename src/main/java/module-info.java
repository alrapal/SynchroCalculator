module alrapa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens alrapal to javafx.fxml;
    exports alrapal;
}