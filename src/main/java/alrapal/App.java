package alrapal;

import javafx.application.Application;
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

    private static Scene scene;

    /* mainWindow.setTitle("Synchro Calculator");
    Parent mainRoot = FXMLLoader.load(getClass().getResource("/Resources/mainWindow.fxml"));
    Scene mainWindowScene = new Scene(mainRoot);

        mainWindow.setScene(mainWindowScene);
        mainWindow.setResizable(false);
        mainWindow.show();*/

    @Override
    public void start(Stage mainWindow) throws IOException {
        mainWindow.setTitle("Synchro Calculator");
        scene = new Scene(loadFXML("mainWindow"));
        //setIcon(mainWindow);
        mainWindow.setScene(scene);
        mainWindow.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void setIcon(Stage primaryStage){
        Image icon = new Image(getClass().getResourceAsStream("/alrapal/Images/icon.png"));
        primaryStage.getIcons().add(icon);
    }

    public static void main(String[] args) {
        launch();
    }



}