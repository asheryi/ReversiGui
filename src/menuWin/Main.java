package menuWin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// The main class runs the main menu .
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Welcome to the Reversi game");
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("menuWin.fxml")));
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}