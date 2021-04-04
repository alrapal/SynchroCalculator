package alrapal;

import alrapal.ImportExport.ImportExportClass;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */

public class App extends Application {

    @Override
    public void start(Stage mainWindow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainWindow" + ".fxml"));
        Parent root = fxmlLoader.load();
        MainWindowController controller = fxmlLoader.getController();
        mainWindow.setTitle("Synchro Calculator");
        Scene scene = new Scene(root);
        setIcon(mainWindow);
        mainWindow.setScene(scene);
        mainWindow.show();
        mainWindow.setResizable(false);

        mainWindow.setOnCloseRequest(event -> {
            if (controller.getImportedBase() != controller.getSynchro().getBaseDamage()) {
                ImportExportClass export = controller.getImportExportClass();
                float baseDamage = controller.getSynchro().getBaseDamage();
                export.exportConfig(baseDamage);
            }
            Platform.exit();
        });
    }

    private void setIcon(Stage primaryStage){
        Image icon = new Image(getClass().getResourceAsStream("/alrapal/Images/icon.png"));
        primaryStage.getIcons().add(icon);
    }

    public static void main(String[] args) {
        launch();
    }



}